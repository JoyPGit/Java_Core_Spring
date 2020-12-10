package com.todo.restfulwebservices.controller;

import com.sun.tools.jconsole.JConsoleContext;
import com.todo.restfulwebservices.model.ToDoEntity;
import com.todo.restfulwebservices.services.ToDoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/base")
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping("/todos")
    public ResponseEntity<List<ToDoEntity>> getAllTodos(){
        // add cast
        return (ResponseEntity<List<ToDoEntity>>)
                ResponseEntity.ok().body(service.getAllTodos());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<ToDoEntity> getTodo(@PathVariable long id){
        return ResponseEntity.ok().body(service.getTodoByID(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoEntity> createTodo(@RequestBody ToDoEntity todoObject){
        return ResponseEntity.ok().body(service.createTodo(todoObject));
    }

    /*
    * For update if id is sent, then should i send the id or whole body?
    * body params will be needed to update, so send whole body
    * */
    @PutMapping("/update/{todo_id}")
    public void updateTodo(@RequestBody ToDoEntity body, @PathVariable long todo_id){
        ResponseEntity.ok().body(service.updateTodo(body, todo_id));
    }

    // for delete i will send id not body
    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable long id){
        System.out.println("id "+id);
        service.deleteTodo(id);
        ResponseEntity.ok();
    }
}
