package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.msd.elearningapp.domain.Assignment;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.AssignmentRepository;

@RestController
@CrossOrigin(origins = {"*"})
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
              assignment.setAssigName(newAssignment.getAssigName());
              assignment.setAssigDatestart(newAssignment.getAssigDatestart());
              assignment.setAssigDateEnd(newAssignment.getAssigDateEnd());
              assignment.setAssigStarter(newAssignment.getAssigStarter());
              assignment.setAssigMem(newAssignment.getAssigMem());
              assignment.setAssigState(newAssignment.getAssigState());
              assignment.setAssigWorkgroup(newAssignment.getAssigWorkgroup());
              assignment.setAssigGrade(newAssignment.getAssigGrade());
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