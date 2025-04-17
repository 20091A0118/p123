package com.backend.task.backend_task_app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.task.backend_task_app.dto.UserRequestDto;
import com.backend.task.backend_task_app.service.UserService;



@RestController
@RequestMapping("/task/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register")
	public String register(@RequestBody UserRequestDto userDto) {
		
		userService.create(userDto);
		return "User is registered";
	}

}
