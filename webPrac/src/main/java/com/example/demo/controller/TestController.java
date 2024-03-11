package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("test")
public class TestController {
	@GetMapping
	public String testController() {
		return "hello world";
	}
	//Post req
	@PostMapping
	public String testPost() {
		return "post test";
	}
	
	//하위 주소 매핑
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "hello wolrd! testGetMapping";
	}
	//PathVariable: test/1235455
	@GetMapping("/{id}")
	public String testControllerWithPathVariables(@PathVariable(required = true) int id) {
//		return testController() + id;
		return "hello world!, id: " + id;
	}
	//위의 pathvariable보다 우선되어 배정
	@GetMapping("/3")
	public String testControllerWithSameValue() {
		return "3";		
	}
	//RequestParam, .../testRequestParam/?id=1
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required = false) int id) {
		return "hello world! id : " + id;
	}
	//RequestBody -- 예제에서는 json obj로 요청
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "hello world" + testRequestBodyDTO.getId() + " message : " + testRequestBodyDTO.getMessage();
	}
	//ResponseBody
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody(){
		List<String> list = new java.util.ArrayList<>();
		list.add("hello world! i'm responseDTO");
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;		
	}
	
//	헤더와 http status를 입력할 수 있다.
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity(){
		List<String> list = new ArrayList<>();
		list.add("Hello world, i'm responseEntity, and you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		//http status 400으로 설정
		return ResponseEntity.badRequest().body(response);
//		return ResponseEntity.ok().body(response);
	}
		
	

	

}
