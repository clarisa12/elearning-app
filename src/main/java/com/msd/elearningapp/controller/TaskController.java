package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.msd.elearningapp.domain.Task;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.TaskRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
class TaskController {

  private final TaskRepository repository;

  TaskController(TaskRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/tasks")
  List<Task> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/tasks")
  Task newTask(@RequestBody Task newTask) {
    return repository.save(newTask);
  }

  // Single item
  
  @GetMapping("/tasks/{id}")
  Task one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/tasks/{id}")
  Task replaceTask(@RequestBody Task newTask, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(task -> {
    	  task.setTskStartDate(task.getTskStartDate());
  		task.setTskEndDate(task.getTskStartDate());
  		task.setTskDescription(task.getTskDescription());
  		task.setTskStudResponsible(task.getTskStudResponsible());
  		task.setTskAssig(task.getTskAssig());
  		task.setTskState(task.getTskState());
        return repository.save(task);
      })
      .orElseGet(() -> {
        newTask.setTskId(id);
        return repository.save(newTask);
      });
  }

  @DeleteMapping("/tasks/{id}")
  void deleteTask(@PathVariable Long id) {
    repository.deleteById(id);
  }
}