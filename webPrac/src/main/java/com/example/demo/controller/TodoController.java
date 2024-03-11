package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.TodoService;
import com.example.demo.model.TodoEntity;
import com.example.demo.dto.TodoDTO;
import com.example.demo.dto.ResponseDTO;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping("test")
	public ResponseEntity<?> testTodoResponseEntity(){
		String str = service.testService();
		List<String> list = new ArrayList<>();
//		list.add("hello Todo Entity response");
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();		
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String tmpUserId = "temporary-user"; //temporary user id
			
			//1. todo entity로 변환
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			//2. id를 null로 초기화, 생성당시에는 id가 없어야함
			System.out.println("todocontroller: createTodo: id before setId()" + entity.getId());
			entity.setId(null);
			
			//3. 임시 사용자 아이디 설정, 4장에서 수정할 예정
			entity.setUserId(tmpUserId);
			
			//4. 서비스를 이용해 Todo 엔티티 생성
			List<TodoEntity> entities = service.create(entity);
			
			//5. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			//6. 변환된 TOdoDTO 리스트를 이용해 ResponseDTO를 초기화
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			//7. ResponseDTO를 리턴
			return ResponseEntity.ok().body(response);
		} catch(Exception e) {
			String error = e.getMessage();;
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<?> retreiveTodoList() {
		String temporaryUserId = "temporary-user";
		
		//1. 서비스의 retrieve() 메서드로 Todo리스트 가져오기
		List<TodoEntity> entities = service.retreive(temporaryUserId);
		
		//2. 자바스트림으로 엔티티리스트를 TodoDTO 리스트로 변환
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//3. 변환된 리스트를 이용해 ResponseDTO를 초기화
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		//4. REsponseDTo리턴
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
		String tempUserId = "temporary-user"; // tmpoerary user id
		
		//1.  dto -> entity
		TodoEntity entity = dto.toEntity(dto);
		
		//2. userid 초기화, 4장 인증과 인가에서 수정예정
		entity.setUserId(tempUserId);
		
		//3. 서비스를 이용해 entity 업데이트
		List<TodoEntity> entities = service.update(entity);
		
		// 4. 자바스트림을 이용해 엔티티 리스트를 TodoDTO리스트로 변환
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//5. ResponseDTO 초기화
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
		try {
			String tempUserId = "temporary-user";
			
			//1. entity로 변환
			TodoEntity entity = dto.toEntity(dto);
			
			//2. 임시 사용자 아이디 설정, 4장 인증과 인가에서 수정할 예정.
			entity.setUserId(tempUserId);
			
			//3. 삭제
			List<TodoEntity> entities = service.delete(entity);
			
			//4. 엔티티리스트 -> TodoDTO리스트
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			//5. ResponseDTO 초기화
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			//6. 리턴
			return ResponseEntity.ok().body(response);
			
		} catch(Exception e) {
			//예외 시
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.ok().body(response);
			
		}
	}

}
