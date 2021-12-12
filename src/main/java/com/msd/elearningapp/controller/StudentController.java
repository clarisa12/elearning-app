package com.msd.elearningapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Student;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.StudentRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	// get all students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}		
	
	// create student rest api
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	// get student by id rest api
	@GetMapping("/students/{studId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long studId) {
		Student student = studentRepository.findById(studId)
				.orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id :" + studId));
		return ResponseEntity.ok(student);
	}
	
	// update student rest api
	
	@PutMapping("/students/{studId}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long studId, @RequestBody Student studentDetails){
		Student student = studentRepository.findById(studId)
				.orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id :" + studId));
		
		student.setPersFirstName(student.getPersFirstName());
		student.setPersLastName(student.getPersLastName());
		student.setPersAdress(student.getPersAdress());
		student.setPersDoB(student.getPersDoB());
		student.setPersEmail(student.getPersEmail());
		student.setPersNum(student.getPersNum());
		student.setPersPhone(student.getPersPhone());
		student.setStudId(student.getStudId());
		student.setStudFaculty(student.getStudFaculty());
		student.setStudSpecialization(student.getStudSpecialization());
		
		Student updatedStudent = studentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	// delete student rest api
	@DeleteMapping("/students/{studId}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long studId){
		Student student = studentRepository.findById(studId)
				.orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id :" + studId));
		
		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
