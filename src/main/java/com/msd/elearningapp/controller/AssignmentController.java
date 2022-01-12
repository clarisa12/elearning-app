package com.msd.elearningapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Assignment;
import com.msd.elearningapp.domain.Workgroup;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.AssignmentRepository;

@RestController
@CrossOrigin(origins = { "*" })
class AssignmentController {

	private final AssignmentRepository repository;

	AssignmentController(AssignmentRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/assignments")
	public List<Assignment> all() {
		return repository.findAll();
	}

	@PostMapping("/assignments")
	public Assignment newAssignment(@RequestBody @Valid Assignment newAssignment) {
		return repository.save(newAssignment);
	}

	// Single item

	@GetMapping("/assignments/{id}")
	public Assignment one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	// ----
	@GetMapping("assignments/{id}/assigWorkgroup")
	// public ResponseEntity<List<Workgroup>>
	// getAssignmentWorkgroupsById(@PathVariable int id) {
	public ResponseEntity<Workgroup> getAssignmentWorkgroupsById(@PathVariable int id) {
		Assignment assignment = repository.findById((long) id)
				.orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " doesn't exist"));

		return ResponseEntity.ok(assignment.getAssigWorkgroup());
	}

	@PutMapping("/assignments/{id}")
	public Assignment replaceAssignment(@RequestBody Assignment newAssignment, @PathVariable Long id) {

		return repository.findById(id).map(assignment -> {
			assignment.setAssigName(newAssignment.getAssigName());
			assignment.setAssigDatestart(newAssignment.getAssigDatestart());
			assignment.setAssigStarter(newAssignment.getAssigStarter());
			assignment.setAssigMem(newAssignment.getAssigMem());
			assignment.setAssigState(newAssignment.getAssigState());
			assignment.setAssigWorkgroup(newAssignment.getAssigWorkgroup());
          assignment.setAssigTask(newAssignment.getAssigTask());
          assignment.setAssigGrade(newAssignment.getAssigGrade());
			return repository.save(assignment);
		}).orElseGet(() -> {
			newAssignment.setAssigId(id);
			return repository.save(newAssignment);
		});
	}

	@DeleteMapping("/assignments/{id}")
	public void deleteAssignment(@PathVariable Long id) {
		repository.deleteById(id);
	}
}