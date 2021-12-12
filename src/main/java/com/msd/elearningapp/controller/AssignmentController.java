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

import com.msd.elearningapp.domain.Assignment;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.AssignmentRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class AssignmentController {

	@Autowired
	private AssignmentRepository assignmentRepository;
	
	// get all assignments
	@GetMapping("/assignments")
	public List<Assignment> getAllAssignments(){
		return assignmentRepository.findAll();
	}		
	
	// create assignment rest api
	@PostMapping("/assignments")
	public Assignment createStudent(@RequestBody Assignment assignment) {
		return assignmentRepository.save(assignment);
	}
	
	// get assignment by id rest api
	@GetMapping("/assignments/{assigId}")
	public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long assigId) {
		Assignment assignment = assignmentRepository.findById(assigId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment does not exist with id :" + assigId));
		return ResponseEntity.ok(assignment);
	}
	
	// update assignment rest api
	
	@PutMapping("/assignments/{assigId}")
	public ResponseEntity<Assignment> updateAssignment(@PathVariable Long assigId, @RequestBody Assignment assignmentDetails){
		Assignment assignment = assignmentRepository.findById(assigId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment not exist with id :" + assigId));
		
		assignment.setassigDatestart(assignmentDetails.getassigDatestart());
		assignment.setassigDateEnd(assignmentDetails.getassigDateEnd());
		assignment.setassigName(assignmentDetails.getassigName());
		assignment.setassigStarter(assignmentDetails.getassigStarter());
		assignment.setassigMem(assignmentDetails.getassigMem());
		
		Assignment updatedAssignment = assignmentRepository.save(assignment);
		return ResponseEntity.ok(updatedAssignment);
	}
	
	// delete assignment rest api
	@DeleteMapping("/assignments/{assigId}")
	public ResponseEntity<Map<String, Boolean>> deleteAssignment(@PathVariable Long assigId){
		Assignment assignment = assignmentRepository.findById(assigId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment does not exist with id :" + assigId));
		
		assignmentRepository.delete(assignment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
