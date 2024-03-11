package com.example.cruddemo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.cruddemo.model.*;
import com.example.cruddemo.dto.*;
import com.example.cruddemo.service.MyService;


@RestController
@RequestMapping
public class Controller {
	@Autowired
	private MyService service;
	
	/* CREATE */
	@GetMapping("/createDummy")
	public ResponseEntity<?> createDummy(){
		try {
		
			final int totalclassroom = 10;
			ClassroomEntity[] classrooms = new ClassroomEntity[totalclassroom];
			//add 10 classrooms
			{
				ClassroomEntity temp;
				for(int i=0; i<totalclassroom; ++i) {
					temp = ClassroomEntity.builder().title("classroom" + i).build();
					classrooms[i] = temp;
					service.addClassroom(temp);
				}
			}
			
			//add 100 students
			{
				StudentEntity temp;
				for(int i=0; i<100; ++i) {
					temp = StudentEntity.builder().name(String.format("%dth student", i))
							.sex(i%3 == 0 ? "M" : "F")
							.classroom(classrooms[i/10])
							.build();
					System.out.println(temp);
					service.addStudnet(temp);
				}
				
			}
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(ex.getStackTrace());
			
		}
		
		
		return ResponseEntity.ok().body("dummy data created");
	}
	
	@PostMapping("/createStudent")
	public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO) {
		StudentEntity student = convertToEntity(studentDTO);
		
		service.addStudnet(student);
		
		return ResponseEntity.ok().body(studentDTO);
	}
	
	@PostMapping("/createClassroom")
	@GetMapping("/createClassroom")
	public ResponseEntity<?> createClassroom(@RequestParam String title) {
		ClassroomEntity classroom = ClassroomEntity.builder().title(title).build();
		
		service.addClassroom(classroom);
		
		return ResponseEntity.ok().body(classroom);
	}
	
	/* RETREIVE */
	
	@GetMapping("/findStudent/{id}")
	public ResponseEntity<?> findStudentById(@PathVariable(required = true) int id) {
		return ResponseEntity.ok().body(service.findStudent(id));
	}
	@GetMapping("/findClassroom/{id}")
	public ResponseEntity<?> findClassroomById(@PathVariable int id) {
		return ResponseEntity.ok().body(service.findClassroom(id));
	}

	@GetMapping("/loadStudents")
	public ResponseEntity<?> loadStudents() {
		return ResponseEntity.ok().body(service.getAllStudents());
	}
	@GetMapping("/loadClassrooms")
	public ResponseEntity<?> loadClassrooms() {
		return ResponseEntity.ok().body(service.getAllClassrooms());
	}
	
	/* UPDATE */
	@PutMapping("/updateClassroom/{id}/{title}")
	public ResponseEntity<?> updateClassroom(@PathVariable(required = true) int id, @PathVariable String title) {
		ClassroomEntity classroom = service.findClassroom(id);
		boolean isExisted = service.updateClassroom(classroom);
		if(isExisted)
			return ResponseEntity.ok().body(service.findClassroom(id));
		else
			return ResponseEntity.badRequest().body("해당 id " + id + "의 교실은 존재하지 않습니다.");
	}
	
	
	/* DELETE */
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		service.deleteStudent(service.findStudent(id));
		return loadStudents();
	}
	
	@DeleteMapping("/deleteClassroom/{id}")
	public ResponseEntity<?> deleteClassroom(@PathVariable int id) {
		service.deleteClassroom(service.findClassroom(id));
		return loadClassrooms();
	}
	
	
	/* HELPER */
	
	private StudentEntity convertToEntity(StudentDTO studentDto) {
		StudentEntity student = StudentEntity.builder()
								.name(studentDto.getName())
								.sex(studentDto.getSex())
								.classroom(service.findClassroom(studentDto.getClassroomId()))
								.build();
		
		return student;
	}
	
}
