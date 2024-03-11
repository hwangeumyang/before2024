package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TodoDTO;

//아마 내부클래스를 써봤더니 주소가 별도가 되었다. 그냥 클래스를 가져다가 쓰는 느낌인가봄 이쪽에 와서 뭔가를 하는 게 아니라.

//한 메서드에 매핑 중첩은 안되는 듯

//동일 이름, 동일 메서드의 매핑이 겹친다면 서버 자체가 시작되지 않는다.
@RestController
@RequestMapping("ex")
public class ControllerEx {
	@PostMapping("hey") //http://127.0.0.1:8080/Two/hey
	@GetMapping("hey")
	public String heyget() {
		return "hey";
	}
	//return null
	@GetMapping("null")
	public String nul() {
		return null;
	}
	
//	@PostMapping("hey")
	@PostMapping("hey2")
	public String hey2() {
		return "hey2";
	}
	@GetMapping("hey")
	public String returnStr() {
		return "hey";
	}
	@GetMapping("returnInt")
	public int getInt() {
		return 1;
	}
	@GetMapping("returnObj") //Not acceptable
	public Object getObj() {
		return new Object();
	}
	@GetMapping("returnTodoDTO")
	public TodoDTO getTodoDTO() {
		return new TodoDTO();
	}
	@RestController
	class InnerTest{
		@PostMapping("inner") //http://127.0.0.1:8080/inner
		public String testInner() {
			return "inner";
		}
	}

}
