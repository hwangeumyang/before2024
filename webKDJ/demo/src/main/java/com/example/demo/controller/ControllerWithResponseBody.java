package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/testResponseBody")
public class ControllerWithResponseBody {

    @GetMapping("/responseDTO")
    public ResponseDTO<String> testControllerResponseBody() {
        java.util.List<String> list = new java.util.ArrayList<>();
        list.add("I'm ResponseDTO");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        
        return response;
    }
    @GetMapping("/listOfTodoDTO")
    public List<TodoDTO> testControllerTodoDTO() {
       List<TodoDTO> list = new ArrayList<>();

       TodoDTO dto1 = new TodoDTO("id1", "title1", true);
       TodoDTO dto2 = new TodoDTO("id2", "title2", false);
       TodoDTO dto3 = new TodoDTO("id3", "title3", true);

       list.add(dto1);
       list.add(dto2);
       list.add(dto3);

       return list;
    }
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("hello world! i'm responseEntity. and u got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        //http status: 400
        return ResponseEntity.badRequest().body(response);
        //http stauts: 200
        // return ResponseEntity.ok().body(response);
    }
}