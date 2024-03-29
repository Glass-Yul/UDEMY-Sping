package com.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int count = 0;

    static {
        todos.add(new Todo(++count, "name","Learn AWS",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo(++count, "name","Learn DevOps",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo(++count, "name","Learn Full Stack Development",
                LocalDate.now().plusYears(3), false ));
    }

    public List<Todo> findByUsername(String username){
        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++count, username, description, targetDate, false);
        todos.add(todo);
    }
}
