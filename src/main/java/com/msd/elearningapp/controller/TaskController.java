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

import com.msd.elearningapp.domain.Task;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.TaskRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	// get all tasks
	@GetMapping("/tasks")
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}		
	
	// create task rest api
	@PostMapping("/tasks")
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	// get task by id rest api
	@GetMapping("/tasks/{tskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long tskId) {
		Task task = taskRepository.findById(tskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id :" + tskId));
		return ResponseEntity.ok(task);
	}
	
	// update task rest api
	
	@PutMapping("/tasks/{tskId}")
	public ResponseEntity<Task> updateTask(@PathVariable Long tskId, @RequestBody Task taskDetails){
		Task task = taskRepository.findById(tskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id :" + tskId));
		
		task.setTskStartDate(task.getTskStartDate());
		task.setTskEndDate(task.getTskStartDate());
		task.setTskDescription(task.getTskDescription());
		task.setTskStudResponsible(task.getTskStudResponsible());
		task.setTskAssig(task.getTskAssig());
		task.setTskState(task.getTskState());
		
		Task updatedTask = taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
	}
	
	// delete task rest api
	@DeleteMapping("/tasks/{tskId}")
	public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long tskId){
		Task task = taskRepository.findById(tskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id :" + tskId));
		
		taskRepository.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
