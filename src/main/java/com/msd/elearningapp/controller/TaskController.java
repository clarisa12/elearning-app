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

	@GetMapping("/tasks")
	public List<Task> all() {
		return repository.findAll();
	}

	@PostMapping("/tasks")
	public Task newTask(@RequestBody Task newTask) {
		return repository.save(newTask);
	}

	// Single item

	@GetMapping("/tasks/{id}")
	public Task one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@PutMapping("/tasks/{id}")
	public Task replaceTask(@RequestBody Task newTask, @PathVariable Long id) {

		return repository.findById(id).map(task -> {
			task.setTskStartDate(task.getTskStartDate());
			task.setTskEndDate(task.getTskStartDate());
			task.setTskDescription(task.getTskDescription());
			task.setTskStudResponsible(task.getTskStudResponsible());
			task.setTskAssig(task.getTskAssig());
			task.setTskState(task.getTskState());
			return repository.save(task);
		}).orElseGet(() -> {
			newTask.setTskId(id);
			return repository.save(newTask);
		});
	}

	@DeleteMapping("/tasks/{id}")
	public void deleteTask(@PathVariable Long id) {
		Task task = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " doesn't exist"));
		repository.deleteById(id);
	}
}