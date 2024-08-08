package com.in28.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {

    // /hello-world
    // return "Hello World"
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
