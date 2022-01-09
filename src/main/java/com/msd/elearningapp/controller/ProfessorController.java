package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Professor;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.ProfessorRepository;

@RestController
class ProfessorController {

  private final ProfessorRepository repository;

  ProfessorController(ProfessorRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/professors")
  List<Professor> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/professors")
  Professor newProfessor(@RequestBody Professor newProfessor) {
    return repository.save(newProfessor);
  }

  // Single item
  
  @GetMapping("/professors/{id}")
  Professor one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/professors/{id}")
  Professor replaceProfessor(@RequestBody Professor newProfessor, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(professor -> {
    	  professor.setPersFirstName(professor.getPersFirstName());
  		professor.setPersLastName(professor.getPersLastName());
  		professor.setPersAdress(professor.getPersAdress());
  		professor.setPersDoB(professor.getPersDoB());
  		professor.setPersEmail(professor.getPersEmail());
  		professor.setPersPhone(professor.getPersPhone());
  		professor.setProfTitle(professor.getProfTitle());
  		professor.setProfDepartment(professor.getProfDepartment());
  		professor.setProfFaculty(professor.getProfFaculty());
  		professor.setPersPassword(professor.getPersPassword());
        return repository.save(professor);
      })
      .orElseGet(() -> {
        newProfessor.setProfId(id);
        return repository.save(newProfessor);
      });
  }

  @DeleteMapping("/professors/{id}")
  void deleteProfessor(@PathVariable Long id) {
    repository.deleteById(id);
  }
}