package com.example.training.service;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.training.model.TodoEntity;
import com.example.training.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class TodoService {
	
	@Autowired
	TodoRepository repo;
	
	
    public List<TodoEntity> create(final TodoEntity entity) {
        // Validations
    	validate(entity);

        repo.save(entity);
        log.info("Entity id :{} is saved", entity.getId());
        
        return repo.findByUserId(entity.getUserId());
    }
    
    public List<TodoEntity> retrieve(final String userId) {
    	log.info("retrieve Entity and return List By userId");
    	return repo.findByUserId(userId);
    }
    
    public List<TodoEntity> update(final TodoEntity entity) {
    	// 1) 검증
    	validate(entity);
    	
    	// 2) entity id를 통해 todoentity 가져오기.
    	final Optional<TodoEntity> original = repo.findById(entity.getId());
    	
    	
    	original.ifPresent(todo -> {
    		// 3) 반환된 todoentity가 존재하면 값을 새 entity 값으로 덮어씨운다.
    		todo.setTitle(entity.getTitle());
    		todo.setDone(entity.isDone());
    		// 4) 저장
    		repo.save(todo);
    	});
    	
    	//2.3.2. retrieve 메서드를 통해 모든 todo리스트 리턴
    	return retrieve(entity.getUserId());
    }
    
    public List<TodoEntity> delete(final TodoEntity entity) {
    	// 1) 저장할엔티티 유효성 확인
    	validate(entity);
    	
    	try {
    		// 2)삭제
    		repo.delete(entity);
    	} catch(Exception e) {
    		// 3) 에러 로깅 => 익셉션, id
    		log.error("error deleting entity", entity.getId(), e);
    		
    		// 4) 컨트롤러로 익셉션 보내기. db 내부로직 캡슐화를 위해 e 대신 새 exception을 리턴.
    		throw new RuntimeException("error deleting entity " + entity.getId());
    	}
    	
    	// 5) 새로운 todo 리스트 리턴
    	return retrieve(entity.getUserId());
    }
    
    private void validate(final TodoEntity entity) {
    	if(entity == null) {
    		log.warn("entity cannot be null");
    		throw new RuntimeException("entity can't be null");
    	}
    	
    	if(entity.getUserId() == null) {
    		log.warn("unknown user");
    		throw new RuntimeException("Unknown user");
    	}
    }

}