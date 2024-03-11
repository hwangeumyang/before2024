package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.TodoDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.service.TodoService;
import java.util.*;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public ResponseEntity<?> responseEntityWithTodo() {
        TodoDTO todo = TodoDTO.builder().id("id1").title("title1").done(true).build();

        return ResponseEntity.ok().body(todo);
    }

    @GetMapping("/testService")
    public ResponseEntity<?> responseEntityWithService() {
        
        String str = service.testService();

        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();


        return ResponseEntity.ok().body(response);
    }
}
