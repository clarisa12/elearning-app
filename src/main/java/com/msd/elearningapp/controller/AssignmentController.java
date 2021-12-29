package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Assignment;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.AssignmentRepository;

@RestController
class AssignmentController {

  private final AssignmentRepository repository;

  AssignmentController(AssignmentRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/assignments")
  List<Assignment> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/assignments")
  Assignment newAssignment(@RequestBody Assignment newAssignment) {
    return repository.save(newAssignment);
  }

  // Single item
  
  @GetMapping("/assignments/{id}")
  Assignment one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/assignments/{id}")
  Assignment replaceAssignment(@RequestBody Assignment newAssignment, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(assignment -> {
    	assignment.setAssigName(assignment.getAssigName());
    	assignment.setAssigDatestart(assignment.getAssigDatestart());
    	assignment.setAssigStarter(assignment.getAssigStarter());
    	assignment.setAssigMem(assignment.getAssigMem());
    	assignment.setAssigState(assignment.getAssigState());
        return repository.save(assignment);
      })
      .orElseGet(() -> {
        newAssignment.setAssigId(id);
        return repository.save(newAssignment);
      });
  }

  @DeleteMapping("/assignments/{id}")
  void deleteAssignment(@PathVariable Long id) {
    repository.deleteById(id);
  }
}