package com.backend.task.backend_task_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.task.backend_task_app.model.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {

}

