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

import com.msd.elearningapp.domain.Administrator;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.AdministratorRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class AdministratorController {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	// get all administrators
	@GetMapping("/administrators")
	public List<Administrator> getAllAdministrators(){
		return administratorRepository.findAll();
	}		
	
	// create administrator rest api
	@PostMapping("/administrators")
	public Administrator createAdministrator(@RequestBody Administrator administrator) {
		return administratorRepository.save(administrator);
	}
	
	// get administrator by id rest api
	@GetMapping("/administrators/{adminId}")
	public ResponseEntity<Administrator> getAdministratorById(@PathVariable Long adminId) {
		Administrator administrator = administratorRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Administrator does not exist with id :" + adminId));
		return ResponseEntity.ok(administrator);
	}
	
	// update administrator rest api
	
	@PutMapping("/administrators/{adminId}")
	public ResponseEntity<Administrator> updateAdministrator(@PathVariable Long adminId, @RequestBody Administrator administratorDetails){
		Administrator administrator = administratorRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Administrator does not exist with id :" + adminId));
		
		administrator.setPersFirstName(administrator.getPersFirstName());
		administrator.setPersLastName(administrator.getPersLastName());
		administrator.setPersAdress(administrator.getPersAdress());
		administrator.setPersDoB(administrator.getPersDoB());
		administrator.setPersEmail(administrator.getPersEmail());
		administrator.setPersNum(administrator.getPersNum());
		administrator.setPersPhone(administrator.getPersPhone());
		administrator.setAdminTitle(administrator.getAdminTitle());
		administrator.setAdminDepartment(administrator.getAdminDepartment());
		
		Administrator updatedAdministrator = administratorRepository.save(administrator);
		return ResponseEntity.ok(updatedAdministrator);
	}
	
	// delete administrator rest api
	@DeleteMapping("/administrators/{adminId}")
	public ResponseEntity<Map<String, Boolean>> deleteAdministrator(@PathVariable Long adminId){
		Administrator administrator = administratorRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Administrator does not exist with id :" + adminId));
		
		administratorRepository.delete(administrator);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
