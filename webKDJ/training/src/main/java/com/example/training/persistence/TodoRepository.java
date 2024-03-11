package com.example.training.persistence;

import com.example.training.model.TodoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

//    @Query("select * from Todo t where t.userId = ?1")
    List<TodoEntity> findByUserId(String userId);

}