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

import com.msd.elearningapp.domain.Grades;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.GradesRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class GradesController {

	@Autowired
	private GradesRepository gradeRepository;
	
	// get all grades
	@GetMapping("/grades")
	public List<Grades> getAllGradess(){
		return gradeRepository.findAll();
	}		
	
	// create grade rest api
	@PostMapping("/grades")
	public Grades createGrades(@RequestBody Grades grade) {
		return gradeRepository.save(grade);
	}
	
	// get grade by id rest api
	@GetMapping("/grades/{gradeId}")
	public ResponseEntity<Grades> getGradesById(@PathVariable Long gradeId) {
		Grades grade = gradeRepository.findById(gradeId)
				.orElseThrow(() -> new ResourceNotFoundException("Grades does not exist with id :" + gradeId));
		return ResponseEntity.ok(grade);
	}
	
	// update grade rest api
	
	@PutMapping("/grades/{gradeId}")
	public ResponseEntity<Grades> updateGrades(@PathVariable Long gradeId, @RequestBody Grades gradeDetails){
		Grades grade = gradeRepository.findById(gradeId)
				.orElseThrow(() -> new ResourceNotFoundException("Grades does not exist with id :" + gradeId));
		
		grade.setGradeDate(grade.getGradeDate());
		grade.setGradeValue(grade.getGradeValue());
		
		Grades updatedGrades = gradeRepository.save(grade);
		return ResponseEntity.ok(updatedGrades);
	}
	
	// delete grade rest api
	@DeleteMapping("/grades/{gradeId}")
	public ResponseEntity<Map<String, Boolean>> deleteGrades(@PathVariable Long gradeId){
		Grades grade = gradeRepository.findById(gradeId)
				.orElseThrow(() -> new ResourceNotFoundException("Grades does not exist with id :" + gradeId));
		
		gradeRepository.delete(grade);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
