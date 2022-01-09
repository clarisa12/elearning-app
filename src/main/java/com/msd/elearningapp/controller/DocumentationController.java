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

import com.msd.elearningapp.domain.Documentation;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.DocumentationRepository;

@RestController
//@RequestMapping("/documentations")
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class DocumentationController {

  private final DocumentationRepository repository;

  public DocumentationController(DocumentationRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/documentations")
  public List<Documentation> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/documentations")
  public Documentation newDocumentation(@RequestBody Documentation newDocumentation) {
    return repository.save(newDocumentation);
  }

  // Single item
  
  @GetMapping("/documentations/{id}")
  public Documentation one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/documentations/{id}")
  public Documentation replaceDocumentation(@RequestBody Documentation newDocumentation, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(documentation -> {
    	documentation.setDocBody(documentation.getDocBody());
    	documentation.setDocObs(documentation.getDocObs());
        return repository.save(documentation);
      })
      .orElseGet(() -> {
        newDocumentation.setIdDoc(id);
        return repository.save(newDocumentation);
      });
  }

  @DeleteMapping("/documentations/{id}")
  public void deleteDocumentation(@PathVariable Long id) {
    repository.deleteById(id);
  }
}