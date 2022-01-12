package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.msd.elearningapp.domain.Student;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.StudentRepository;

@RestController
class StudentController {

  private final StudentRepository repository;

  StudentController(StudentRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @CrossOrigin(origins = "*", maxAge = 3600)
  @GetMapping("/students")
  List<Student> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/students")
  Student newStudent(@RequestBody Student newStudent) {
    return repository.save(newStudent);
  }

  // Single item
  
  @GetMapping("/students/{id}")
  Student one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/students/{id}")
  Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(student -> {
    	student.setPersFirstName(student.getPersFirstName());
  		student.setPersLastName(student.getPersLastName());
  		student.setPersAdress(student.getPersAdress());
  		student.setPersDoB(student.getPersDoB());
  		student.setPersEmail(student.getPersEmail());
  		student.setPersPhone(student.getPersPhone());
  		student.setStudId(student.getStudId());
  		student.setStudFaculty(student.getStudFaculty());
  		student.setStudSpecialization(student.getStudSpecialization());
        return repository.save(student);
      })
      .orElseGet(() -> {
        newStudent.setStudId(id);
        return repository.save(newStudent);
      });
  }

  @DeleteMapping("/students/{id}")
  void deleteStudent(@PathVariable Long id) {
    repository.deleteById(id);
  }
}