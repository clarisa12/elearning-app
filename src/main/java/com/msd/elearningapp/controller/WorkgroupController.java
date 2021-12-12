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

import com.msd.elearningapp.domain.Workgroups;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.WorkgroupsRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class WorkgroupController {

	@Autowired
	private WorkgroupsRepository workgroupRepository;
	
	// get all workgroups
	@GetMapping("/workgroups")
	public List<Workgroups> getAllWorkgroupss(){
		return workgroupRepository.findAll();
	}		
	
	// create workgroup rest api
	@PostMapping("/workgroups")
	public Workgroups createWorkgroups(@RequestBody Workgroups workgroup) {
		return workgroupRepository.save(workgroup);
	}
	
	// get workgroup by id rest api
	@GetMapping("/workgroups/{wrkId}")
	public ResponseEntity<Workgroups> getWorkgroupsById(@PathVariable Long wrkId) {
		Workgroups workgroup = workgroupRepository.findById(wrkId)
				.orElseThrow(() -> new ResourceNotFoundException("Workgroups does not exist with id :" + wrkId));
		return ResponseEntity.ok(workgroup);
	}
	
	// update workgroup rest api
	
	@PutMapping("/workgroups/{wrkId}")
	public ResponseEntity<Workgroups> updateWorkgroups(@PathVariable Long wrkId, @RequestBody Workgroups workgroupDetails){
		Workgroups workgroup = workgroupRepository.findById(wrkId)
				.orElseThrow(() -> new ResourceNotFoundException("Workgroups does not exist with id :" + wrkId));
		
		workgroup.setMentor(workgroup.getMentor());
		workgroup.setWkrList(workgroup.getWkrList());
		workgroup.setWkrName(workgroup.getWkrName());
		
		Workgroups updatedWorkgroups = workgroupRepository.save(workgroup);
		return ResponseEntity.ok(updatedWorkgroups);
	}
	
	// delete workgroup rest api
	@DeleteMapping("/workgroups/{wrkId}")
	public ResponseEntity<Map<String, Boolean>> deleteWorkgroups(@PathVariable Long wrkId){
		Workgroups workgroup = workgroupRepository.findById(wrkId)
				.orElseThrow(() -> new ResourceNotFoundException("Workgroups does not exist with id :" + wrkId));
		
		workgroupRepository.delete(workgroup);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
