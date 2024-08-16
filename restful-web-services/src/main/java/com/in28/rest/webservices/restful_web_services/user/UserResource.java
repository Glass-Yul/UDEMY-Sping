package com.in28.rest.webservices.restful_web_services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping(value = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User retrieveUsers(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFountException("id : "+id);

        }

        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
