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

import com.msd.elearningapp.domain.Professor;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.ProfessorRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;
	
	// get all professors
	@GetMapping("/professors")
	public List<Professor> getAllProfessors(){
		return professorRepository.findAll();
	}		
	
	// create professor rest api
	@PostMapping("/professors")
	public Professor createProfessor(@RequestBody Professor professor) {
		return professorRepository.save(professor);
	}
	
	// get professor by id rest api
	@GetMapping("/professors/{profId}")
	public ResponseEntity<Professor> getProfessorById(@PathVariable Long profId) {
		Professor professor = professorRepository.findById(profId)
				.orElseThrow(() -> new ResourceNotFoundException("Professor does not exist with id :" + profId));
		return ResponseEntity.ok(professor);
	}
	
	// update professor rest api
	
	@PutMapping("/professors/{profId}")
	public ResponseEntity<Professor> updateProfessor(@PathVariable Long profId, @RequestBody Professor professorDetails){
		Professor professor = professorRepository.findById(profId)
				.orElseThrow(() -> new ResourceNotFoundException("Professor does not exist with id :" + profId));
		
		professor.setPersFirstName(professor.getPersFirstName());
		professor.setPersLastName(professor.getPersLastName());
		professor.setPersAdress(professor.getPersAdress());
		professor.setPersDoB(professor.getPersDoB());
		professor.setPersEmail(professor.getPersEmail());
		professor.setPersNum(professor.getPersNum());
		professor.setPersPhone(professor.getPersPhone());
		professor.setProfTitle(professor.getProfTitle());
		professor.setProfDepartment(professor.getProfDepartment());
		professor.setProfFaculty(professor.getProfFaculty());
		
		Professor updatedProfessor = professorRepository.save(professor);
		return ResponseEntity.ok(updatedProfessor);
	}
	
	// delete professor rest api
	@DeleteMapping("/professors/{profId}")
	public ResponseEntity<Map<String, Boolean>> deleteProfessor(@PathVariable Long profId){
		Professor professor = professorRepository.findById(profId)
				.orElseThrow(() -> new ResourceNotFoundException("Professor does not exist with id :" + profId));
		
		professorRepository.delete(professor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
