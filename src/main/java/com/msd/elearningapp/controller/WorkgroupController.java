package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.msd.elearningapp.domain.Workgroup;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.WorkgroupRepository;

@CrossOrigin(origins = "*")
@RestController
class WorkgroupsController {

  private final WorkgroupRepository repository;

  WorkgroupsController(WorkgroupRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @CrossOrigin(origins = "*")
  @GetMapping("/workgroups")
  List<Workgroup> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @CrossOrigin(origins = "*")
  @PostMapping("/workgroups")
  Workgroup newWorkgroups(@RequestBody Workgroup newWorkgroups) {
    return repository.save(newWorkgroups);
  }

  // Single item
  @CrossOrigin(origins = "*")
  @GetMapping("/workgroups/{id}")
  Workgroup one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/workgroups/{id}")
  Workgroup replaceWorkgroups(@RequestBody Workgroup newWorkgroups, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(workgroup -> {
    	workgroup.setWrkAssig(newWorkgroups.getWrkAssig());
  		workgroup.setWrkList(newWorkgroups.getWrkList());
  		workgroup.setWrkName(newWorkgroups.getWrkName());
        return repository.save(workgroup);
      })
      .orElseGet(() -> {
        newWorkgroups.setWrkId(id);
        return repository.save(newWorkgroups);
      });
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping("/workgroups/{id}")
  void deleteWorkgroups(@PathVariable Long id) {
    repository.deleteById(id);
  }
}