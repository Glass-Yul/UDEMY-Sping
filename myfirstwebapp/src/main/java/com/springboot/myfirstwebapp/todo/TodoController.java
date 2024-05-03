package com.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

//    public TodoController(TodoService todoService) {
//        super();
//        this.todoService = todoService;
//    }
    @Autowired
    private TodoService todoService;


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = (String) model.get("name");
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    private String getLoggedInUsername(ModelMap model) {
        // 현재 실행 중인 스레드의 보안 컨텍스트에서 인증 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 가져온 인증 정보에서 사용자 이름을 반환합니다.
        return authentication.getName();
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = (String) model.getAttribute("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(Model model, @Valid Todo todo ) {
        String username = (String) model.getAttribute("name");
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {

        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(Model model, @Valid Todo todo) {

        String username = (String) model.getAttribute("name");
        todo.setUsername(username);

        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }



}
