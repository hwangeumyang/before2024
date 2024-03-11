package com.example.cruddemo.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.example.cruddemo.model.StudentEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	private String name;
	private String sex;
	private int classroomId;
	private String classroomTitle;
	
	public StudentDTO(StudentEntity entity) {
		this.name = entity.getName();
		this.sex = entity.getSex();
		this.classroomTitle = entity.getClassroom().getTitle();
	}

}
