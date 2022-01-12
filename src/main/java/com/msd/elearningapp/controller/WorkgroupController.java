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

import com.msd.elearningapp.domain.Workgroup;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.WorkgroupRepository;

@RestController
@CrossOrigin(origins = { "*" })
class WorkgroupsController {

	private final WorkgroupRepository repository;

	WorkgroupsController(WorkgroupRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/workgroups")
	List<Workgroup> all() {
		return repository.findAll();
	}

	@PostMapping("/workgroups")
	Workgroup newWorkgroups(@RequestBody Workgroup newWorkgroups) {
		return repository.save(newWorkgroups);
	}

	// Single item

	@GetMapping("/workgroups/{id}")
	Workgroup one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@PutMapping("/workgroups/{id}")
	Workgroup replaceWorkgroups(@RequestBody Workgroup newWorkgroups, @PathVariable Long id) {

		return repository.findById(id).map(workgroup -> {
			workgroup.setWrkAssig(newWorkgroups.getWrkAssig());
			workgroup.setWrkList(newWorkgroups.getWrkList());
			workgroup.setWrkName(newWorkgroups.getWrkName());
			return repository.save(workgroup);
		}).orElseGet(() -> {
			newWorkgroups.setWrkId(id);
			return repository.save(newWorkgroups);
		});
	}

	@DeleteMapping("/workgroups/{id}")
	void deleteWorkgroups(@PathVariable Long id) {
		repository.deleteById(id);
	}
}