package com.todo.restfulwebservices.repository;

import com.todo.restfulwebservices.model.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// repository extends JpaRepository
// pass Model and Id type (Long here)

//
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {

}
