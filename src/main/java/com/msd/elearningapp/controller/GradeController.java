package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Grade;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.GradeRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
class GradeController {

	private final GradeRepository repository;

	GradeController(GradeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/grades")
	public List<Grade> all() {
		return repository.findAll();
	}

	@PostMapping("/grades")
	public Grade newGrade(@RequestBody Grade newGrade) {
		return repository.save(newGrade);
	}

	// Single item

	@GetMapping("/grades/{id}")
	public Grade one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@PutMapping("/grades/{id}")
	public Grade replaceGrade(@RequestBody Grade newGrade, @PathVariable Long id) {

		return repository.findById(id).map(grade -> {
			grade.setGradeDate(grade.getGradeDate());
			grade.setGradeValue(grade.getGradeValue());
			return repository.save(grade);
		}).orElseGet(() -> {
			newGrade.setGradeId(id);
			return repository.save(newGrade);
		});
	}

	@DeleteMapping("/grades/{id}")
	public void deleteGrade(@PathVariable Long id) {
		repository.deleteById(id);
	}
}