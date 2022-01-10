package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Administrator;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.AdministratorRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
class AdministratorController {

	private final AdministratorRepository repository;

	AdministratorController(AdministratorRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/administrators")
	List<Administrator> all() {
		return repository.findAll();
	}

	@PostMapping("/administrators")
	Administrator newAdministrator(@RequestBody Administrator newAdministrator) {
		return repository.save(newAdministrator);
	}

	// Single item

	@GetMapping("/administrators/{id}")
	Administrator one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@PutMapping("/administrators/{id}")
	Administrator replaceAdministrator(@RequestBody Administrator newAdministrator, @PathVariable Long id) {

		return repository.findById(id).map(administrator -> {
			administrator.setPersFirstName(administrator.getPersFirstName());
			administrator.setPersLastName(administrator.getPersLastName());
			administrator.setPersAdress(administrator.getPersAdress());
			administrator.setPersDoB(administrator.getPersDoB());
			administrator.setPersEmail(administrator.getPersEmail());
			administrator.setPersPassword(administrator.getPersPassword());
			administrator.setPersPhone(administrator.getPersPhone());
			administrator.setAdminTitle(administrator.getAdminTitle());
			administrator.setAdminDepartment(administrator.getAdminDepartment());
			return repository.save(administrator);
		}).orElseGet(() -> {
			newAdministrator.setAdminId(id);
			return repository.save(newAdministrator);
		});
	}

	@DeleteMapping("/administrators/{id}")
	void deleteAdministrator(@PathVariable Long id) {
		repository.deleteById(id);
	}
}