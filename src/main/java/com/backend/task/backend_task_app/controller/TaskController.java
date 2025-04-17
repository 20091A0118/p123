package com.backend.task.backend_task_app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.task.backend_task_app.dto.TaskRequestDto;
import com.backend.task.backend_task_app.model.Task;
import com.backend.task.backend_task_app.service.TaskService;



@RestController
@RequestMapping("/api/task")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/{id}")
	public Task getTask(@PathVariable long id) {
		return taskService.getTaskById(id);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Task> allTasks() {
		return taskService.getAll();
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public String createTask(@RequestBody TaskRequestDto taskDto) {
		 taskService.save(taskDto);
		 return "Task is created";
	}

}
