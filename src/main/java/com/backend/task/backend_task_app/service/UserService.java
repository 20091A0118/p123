package com.backend.task.backend_task_app.service;


import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.task.backend_task_app.dto.UserRequestDto;
import com.backend.task.backend_task_app.model.Role;
import com.backend.task.backend_task_app.model.User;
import com.backend.task.backend_task_app.repository.RoleRepository;
import com.backend.task.backend_task_app.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	public void create(UserRequestDto userDto) {
		
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedString=encoder.encode(userDto.getPassword());
		
		User user =new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encodedString);
		
		userRepository.save(user);
		
		
	}

	public User assignRoleToUser(String username, String role) {
		  Optional<User> userOpt=userRepository.findByUsername(username);
		  Optional<Role> roleOpt=roleRepository.findByRoleName(role);
		  
		  if(userOpt.isPresent() && roleOpt.isPresent()) {
			  User user=userOpt.get();
			  Role role1 = roleOpt.get();
			  user.getRoles().add(role1);
			  return userRepository.save(user);
			  
			  }
		  throw new RuntimeException("User or Role not found");
	}

}
