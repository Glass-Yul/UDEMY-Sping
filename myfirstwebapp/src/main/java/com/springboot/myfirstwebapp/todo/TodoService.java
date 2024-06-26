package com.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        // 사용자 이름이 일치하는 Todo 객체를 필터링하기 위한 Predicate을 정의합니다.
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username); // 존재 확인

        // Todo 목록을 스트림으로 변환하고, Predicate을 사용하여 사용자 이름이 일치하는 Todo 객체를 필터링합니다.
        // 그 후, 필터링된 Todo 객체들을 리스트로 변환하여 반환합니다.
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++count, username, description, targetDate, false);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id; // 존재 확인
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id; // 존재 확인
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
