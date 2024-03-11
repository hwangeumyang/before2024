package com.example.training.controller;

import com.example.training.dto.ResponseDTO;
import com.example.training.dto.TodoDTO;
import com.example.training.model.TodoEntity;
import com.example.training.service.TodoService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String tempUserId = "temporary-user";
			
			// 1) Dto 2 Entity
			TodoEntity entity = dto.toEntity(dto);
			
			// 2) initiate id to null, 생성당시에는 id가 없다.
			entity.setId(null);
			
			// 3) 임시 사용자 id 설정, 인증인가에서 수정. 지금은 temporary-user만 로그인 없이 사용할 수 있는 앱
			entity.setUserId(tempUserId);
			
			// 4) create Todo entity by service
			List<TodoEntity> entities = service.create(entity);
			
			// 5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 tododto 리스트로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// 6) 변환된 TodoDTO 리스틀플 이용해 ResponseDTO를 초기화
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			// 7) ResponseDTO 리턴
			return ResponseEntity.ok().body(response);
			
		} catch(Exception e) {
			String err = e.getMessage();
			System.out.println(e.getMessage());
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(err).build();
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveTodo(final String userId) {
		
			
			String tmpuserId = "temporary-user";
			
			// 1) 서비스를 이용해 Todo List 가져오기
			List<TodoEntity> entities = service.retrieve(tmpuserId);
			
			// 2) 엔티티 리스트를 DTO 리스트로
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// 3) ResponseDTO 초기화 by DTO list
			
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
									.data(dtos)
									.build();
			
			// ResponseDTO 리턴
			
			return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
		String temporaryUserId = "temporary-user";
		
		// 1) dto -> entity
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		// 2) id를 초기화, 4장 인증과 인가에서 수정
		entity.setUserId(temporaryUserId);
		
		// 3) 서비스로  entity update
		List<TodoEntity> entities = service.update(entity);
		
		// 4) List<TodoEntity> -> List<TodoDTO> by stream
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		// 5) ResponseDTO 초기화 by List<TodoDTO>
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
										.data(dtos)
										.build();
		// 6) ResponseDTO 리턴
		return ResponseEntity.ok().body(response);	
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user";
			
			// 1) to Entity
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			// 2) 임시사용자 아이디 작성
			entity.setUserId(temporaryUserId);
			
			// 3) entity 삭제
			List<TodoEntity> entities = service.delete(entity);
			
			// 4) list entities -> list dtos
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// 5) responsedTO 초기화
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
											.data(dtos).build();
			
			// 6) 리턴
			return ResponseEntity.ok().body(response);
			
		} catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.ok(response);
		}
	}

}
