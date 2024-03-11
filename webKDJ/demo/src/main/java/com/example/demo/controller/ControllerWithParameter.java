package com.example.demo.controller;

import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/testParam")
public class ControllerWithParameter {
	@GetMapping(path = {"/"})
	public String test() {
		return "test";
	}
	@GetMapping(path = {"/PathVariable", "/PathVariable/{id}"})
	public String testControllerWithPathVariables(@PathVariable(required = false) String id) {
		return "id is " + id;
	}
	@GetMapping("/MultiplePathVariable/{id}/{comment}")
	public String testControllerWithMultiplePathVariables(@PathVariable(required = false) int id, @PathVariable(required = true) String comment) {
		return id + " " + comment;
	}
	
	@GetMapping(path = {"/PathVariableRequired", "/PathVariableRequired/{id}"})
	public String testControllerWithRequiredPathVariables(@PathVariable(required = true) String id) {
		return "id is " + id;
	}
	
	@GetMapping("/RequestParam")
	public String testControllerWithRequestParam(@RequestParam(required = false, defaultValue = "0") int id) {
		return "id is " + id;
	}
	
	@GetMapping("/requestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return String.format("RequestBody TEST => id : %d, msg : %s", testRequestBodyDTO.getId(), testRequestBodyDTO.getMessage()); 
	}

}
