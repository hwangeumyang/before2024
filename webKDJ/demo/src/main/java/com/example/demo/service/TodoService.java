package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

@Service
public class TodoService {

    @Autowired
    TodoRepository repository;

    public String testService() {
        // create TodoEntity 
        TodoEntity entity = TodoEntity.builder().title("my first todo item").build();
        // save TodoEntity
        repository.save(entity);
        // retreive TodoEntity 
        // TodoEntity savedEntity = repository.findById(entity.getId()).get();
        TodoEntity savedEntity = repository.findByUserId(entity.getUserId()).get(0);


        return savedEntity.getTitle();
    }

    

}