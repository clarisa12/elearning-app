package com.msd.elearningapp.controller;

import java.util.List;

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
import com.msd.elearningapp.domain.Workgroup;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.StudentRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
class StudentController {

	private final StudentRepository repository;

	StudentController(StudentRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/students")
	public List<Student> all() {
		return repository.findAll();
	}

	@PostMapping("/students")
	public Student newStudent(@RequestBody Student newStudent) {
		return repository.save(newStudent);
	}

	// Single item

	@GetMapping("/students/{id}")
	public Student one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

	}

	@GetMapping("studentes/{id}/workgroups")
	public ResponseEntity<List<Workgroup>> getStudentWorkgroupById(@PathVariable int id) {
		Student student = repository.findById((long) id).orElseThrow(() -> new ResourceNotFoundException((long) id));

		return ResponseEntity.ok(student.getWorkgroups());
	}

	@PutMapping("/students/{id}")
	public Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {

		return repository.findById(id).map(stud -> {
			stud.setPersFirstName(stud.getPersFirstName());
			stud.setPersLastName(stud.getPersLastName());
			stud.setPersAdress(stud.getPersAdress());
			stud.setPersDoB(stud.getPersDoB());
			stud.setPersEmail(stud.getPersEmail());
			stud.setPersEmail(stud.getPersPassword());
			stud.setPersPhone(stud.getPersPhone());
			stud.setStudSpecialization(stud.getStudSpecialization());
			stud.setWorkgroups(stud.getWorkgroups());
			stud.setStudFaculty(stud.getStudFaculty());
			stud.setPersPassword(stud.getPersPassword());
			return repository.save(stud);
		}).orElseGet(() -> {
			newStudent.setStudId(id);
			return repository.save(newStudent);
		});
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable Long id) {
		repository.deleteById(id);
	}
}