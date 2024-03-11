package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/test/test2")
public class TestTestController {
	
	@GetMapping
	public String get() {
		return "test/test2:GET";
	}

}
