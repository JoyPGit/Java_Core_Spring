package com.todo.restfulwebservices.services;

import com.todo.restfulwebservices.model.ToDoEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface TodoServiceTemplate {
   ToDoEntity createTodo(ToDoEntity todo);
   ToDoEntity updateTodo(ToDoEntity todo, long id);
   boolean exists(ToDoEntity todo);
   List<ToDoEntity> getAllTodos();
   ToDoEntity getTodoByID(long id);
   void deleteTodo(long id);
}

// create an interface and then use a service class to implement
// @Service annotation