package com.backend.task.backend_task_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.task.backend_task_app.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	    Optional<Role> findByRoleName(String name);

}