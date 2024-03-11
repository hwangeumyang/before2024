package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	
//	쿼리를 직접 작성하는 경우
//	@Query("select * from Todo t where t.userId=?1")
	//스프링 데이터 jpa가 메서드 이름을 파싱해서 자동적으로 쿼리 작성
	java.util.List<TodoEntity> findByUserId(String userId);
	

}
