package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllerWithoutRequestMapping {
	@GetMapping("testGET")
	public String testGet() {
		return "TestControllerWithoutRequestMapping::GET";
	}

}
