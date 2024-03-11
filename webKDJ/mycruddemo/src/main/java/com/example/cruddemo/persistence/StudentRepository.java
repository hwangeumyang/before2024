package com.example.cruddemo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.cruddemo.model.StudentEntity;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

}
