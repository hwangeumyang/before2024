package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("test") // resource
public class TestController {
	@GetMapping() // /test, /test/는 안됐음.
	public String testController() {
		return "Hello World";
	}
	
	@GetMapping("/testGetMapping/") // /test/testGetMapping/ , 마지막에 슬래시가 들어감.
	public String testControllerWithPath() {
		return "/test/testGetMapping/";
	}
	@GetMapping("/test/test") // /test/test/test
	public String testControllerWithPath2() {
		return "/test/test/test";
	}
	
	@GetMapping("WithPath3") //  /test/WithPath3
	public String testControllerWithPath3() {
		return "WithPath3";
	}
	
	@RestController
	@RequestMapping("nestedController")
	class TestNestedController {
		@GetMapping()
		public String get() {
			return "nestedControl";
		}
	}
	
}
