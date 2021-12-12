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

import com.msd.elearningapp.domain.Documentation;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.DocumentationRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class DocumentationController {

	@Autowired
	private DocumentationRepository documentationRepository;
	
	// get all documentations
	@GetMapping("/documentations")
	public List<Documentation> getAllDocumentations(){
		return documentationRepository.findAll();
	}		
	
	// create documentation rest api
	@PostMapping("/documentations")
	public Documentation createDocumentation(@RequestBody Documentation documentation) {
		return documentationRepository.save(documentation);
	}
	
	// get documentation by id rest api
	@GetMapping("/documentations/{idDoc}")
	public ResponseEntity<Documentation> getDocumentationById(@PathVariable Long idDoc) {
		Documentation documentation = documentationRepository.findById(idDoc)
				.orElseThrow(() -> new ResourceNotFoundException("Documentation does not exist with id :" + idDoc));
		return ResponseEntity.ok(documentation);
	}
	
	// update documentation rest api
	
	@PutMapping("/documentations/{idDoc}")
	public ResponseEntity<Documentation> updateDocumentation(@PathVariable Long idDoc, @RequestBody Documentation documentationDetails){
		Documentation documentation = documentationRepository.findById(idDoc)
				.orElseThrow(() -> new ResourceNotFoundException("Documentation does not exist with id :" + idDoc));
		
		documentation.setDocObs(documentation.getDocObs());
		documentation.setDocBody(documentation.getDocBody());
		
		Documentation updatedDocumentation = documentationRepository.save(documentation);
		return ResponseEntity.ok(updatedDocumentation);
	}
	
	// delete documentation rest api
	@DeleteMapping("/documentations/{idDoc}")
	public ResponseEntity<Map<String, Boolean>> deleteDocumentation(@PathVariable Long idDoc){
		Documentation documentation = documentationRepository.findById(idDoc)
				.orElseThrow(() -> new ResourceNotFoundException("Documentation does not exist with id :" + idDoc));
		
		documentationRepository.delete(documentation);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
