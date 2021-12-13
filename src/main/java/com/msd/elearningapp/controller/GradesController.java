package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Grades;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.GradesRepository;

@RestController
class GradeController {

  private final GradesRepository repository;

  GradeController(GradesRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/grades")
  List<Grades> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/grades")
  Grades newGrade(@RequestBody Grades newGrade) {
    return repository.save(newGrade);
  }

  // Single item
  
  @GetMapping("/grades/{id}")
  Grades one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/grades/{id}")
  Grades replaceGrade(@RequestBody Grades newGrade, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(grade -> {
    	  grade.setGradeDate(grade.getGradeDate());
    	  grade.setGradeValue(grade.getGradeValue());
        return repository.save(grade);
      })
      .orElseGet(() -> {
        newGrade.setGradeId(id);
        return repository.save(newGrade);
      });
  }

  @DeleteMapping("/grades/{id}")
  void deleteGrade(@PathVariable Long id) {
    repository.deleteById(id);
  }
}