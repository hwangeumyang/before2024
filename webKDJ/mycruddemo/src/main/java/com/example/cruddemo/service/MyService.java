package com.example.cruddemo.service;


import com.example.cruddemo.persistence.*;
import com.example.cruddemo.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class MyService {
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private ClassroomRepository classroomRepo;

	/* create */
	public void addStudnet(StudentEntity student) {
		studentRepo.save(student);
	}
	
	public void addClassroom(ClassroomEntity classroom) {
		classroomRepo.save(classroom);
	}

	/* retrieve */
	public List<StudentEntity> getAllStudents() {
		return studentRepo.findAll();
	}
	
	public List<ClassroomEntity> getAllClassrooms() {
		return classroomRepo.findAll();
	}
	
	public ClassroomEntity findClassroom(int id) {
		return classroomRepo.findById(id).get();
	}
	public StudentEntity findStudent(int id) {
		return studentRepo.findById(id).get();
	}
	
	/* update */
	
	public boolean updateClassroom(ClassroomEntity classroom) {
		if(classroomRepo.existsById(classroom.getId())) {
			classroomRepo.save(classroom);
			return true;
		}
		return false;
	}
	
	/* delete */
	public void deleteStudent(StudentEntity student) {
		studentRepo.delete(student);
	}
	public void deleteClassroom(ClassroomEntity classroom) {
		classroomRepo.delete(classroom);
	}

}
