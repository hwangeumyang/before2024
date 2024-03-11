package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.model.TodoEntity;
import java.util.Optional;


@Slf4j
@Service
public class TodoService {
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		// creation todoentity
		TodoEntity entity = TodoEntity.builder().title("my forst todoitem").build();
		// save TodoEntity
		repository.save(entity);
		// retreive TodoEntity
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		
		System.out.println("service:testService():: entity's id : " + entity.getId());
		
		
		return savedEntity.getTitle();
	}
	
	public List<TodoEntity> create(final TodoEntity entity) {
		// Validation
		validate(entity);
		
		repository.save(entity);
		log.info("Entity id: {} is saved", entity.getId());
		
		return repository.findByUserId(entity.getUserId());
	}

	public List<TodoEntity> retreive(final String userId) {
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(final TodoEntity entity) {
		//1. 유효성 확인
		validate(entity);
		
		//2. 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 것은 업데이트 x
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(todo -> {
			//3. 반환된 Todo Entity가 존재하면 새 entity값으로 덮처씌움.
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			//4. 데이터베이스에 새 값 저장
			repository.save(todo);
		});

		//람다식을 쓰지 않은 경우
//		if(original.isPresent()) {
//			final TodoEntity todo = original.get();
//			todo.setTitle(entity.getTitle());
//			todo.setDone(entity.isDone());
//			
//			repository.save(todo);
//		}
		
		//모든 todo list 반환.
		return retreive(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity) {
		//1. 엔티티 유효성 검사
		validate(entity);
		
		try {
			//2. 삭제
			repository.delete(entity);
		} catch(Exception e) {
			//3. exception 발생시 id와 excetpion 로깅
			log.error("error deleting entity ", entity.getId(), e);

			//4. 컨트롤러로 익셉션 보냄, db 내부 로직을 캡슐화 하려면 e를 리턴하지 않고 새로운 exception 오브젝트를 리턴
//			throw e;
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		
		return retreive(entity.getUserId());		
	}
	
	public void validate(final TodoEntity entity) {
		String msg = "";
		
		if(entity == null) {
			log.warn("entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}		
		if(entity.getUserId()==null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknoown user.");
		}
	}
	

}
