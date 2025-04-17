package com.backend.task.backend_task_app.service;



import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.stereotype.Service;

import com.backend.task.backend_task_app.dto.TaskRequestDto;
import com.backend.task.backend_task_app.exception.TaskNotFoundException;
import com.backend.task.backend_task_app.model.Task;
import com.backend.task.backend_task_app.repository.TaskRepository;



@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository=taskRepository;
	}
	public Task getTaskById(long id) {
		return taskRepository.findById(id)
				.orElseThrow(()-> new TaskNotFoundException("Task not found"));
	}
	
	public void save(TaskRequestDto taskDto) {
		Task task=new Task();
		task.setTaskName(taskDto.getTaskName());
		task.setDuration(taskDto.getDuration());
	
	}
	public List<Task> getAll() {
		taskRepository.findAll();
		return null;
	}

}

