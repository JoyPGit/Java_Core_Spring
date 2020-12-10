package com.todo.restfulwebservices.services;

import com.todo.restfulwebservices.exceptions.ResourceNotFoundException;
import com.todo.restfulwebservices.model.ToDoEntity;
import com.todo.restfulwebservices.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ToDoService  implements TodoServiceTemplate {

    // why repository class, why not implement directly?
    // to inject instantiation is needed
    @Autowired
    private ToDoRepository todoRepository;


    @Override
    public ToDoEntity createTodo(ToDoEntity todo) {
        return todoRepository.save(todo);
    }

    @Override
    // whole body needs to be sent
    public ToDoEntity updateTodo(ToDoEntity todo, long todo_id) {
        // a check if db entry exists
        // try with null
        Optional<ToDoEntity> entityFromDB = todoRepository.findById(todo_id);
        System.out.println("entity "+entityFromDB);
        // id is private, use getId
        if(entityFromDB.isPresent()){
            // JpaRepo itself extends CrudRepository
            ToDoEntity todoUpdate = todoRepository.getOne(todo.getId());
            todoUpdate.setName(todo.getName());
            todoUpdate.setStartTime(todo.getStartTime());
            todoRepository.save(todoUpdate);
            return todoUpdate;
        }
        else{
            throw new ResourceNotFoundException("no todo with this id found");
        }
    }

    // need to complete this method
    @Override
    public boolean exists(ToDoEntity todo) {
        return false;
    }

    @Override
    public List<ToDoEntity> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public ToDoEntity getTodoByID(long id) {
        Optional<ToDoEntity> entityFromDB = todoRepository.findById(id);
        // id is private, use getId
        if(entityFromDB.isPresent()){
            return todoRepository.getOne(id);
        }
        else{
            throw new ResourceNotFoundException("no todo with this id found");
        }
    }

    /*
    * the method signature needs to be the same, else
    * method doesn't override error is thrown
    * */
    @Override
    public void deleteTodo(long id) {
        Optional<ToDoEntity> entityFromDB = todoRepository.findById(id);
        // id is private, use getId
        if(entityFromDB.isPresent()){
            // findById and getOne differ in return type
            todoRepository.delete(todoRepository.getOne(id));
        }
        else{
            throw new ResourceNotFoundException("no todo with this id found");
        }
    }
}

// implements the interface but gets its methods from repository
// for null, i.e. if entity doesn't exist?
// exception?
// Optional<>
// save creates a new entry, use set
// Repository is always injected, service interface might not be always needed