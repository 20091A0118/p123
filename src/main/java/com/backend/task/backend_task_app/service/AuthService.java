package com.backend.task.backend_task_app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.backend.task.backend_task_app.dto.JwtRequest;
import com.backend.task.backend_task_app.dto.JwtResponse;
import com.backend.task.backend_task_app.jwt.JwtAuthHelper;



@Service
public class AuthService {

	@Autowired
	private JwtAuthHelper jwtHelper;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	public void doAuthenticate(String username, String password) {
		System.out.println("here ==> K");
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			manager.authenticate(authToken);
		} catch(BadCredentialsException b) {
			throw new BadCredentialsException("Invalid Username or Password");
		}
	}

	public JwtResponse login(JwtRequest jwtRequest) {

		this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtHelper.generateToken(userDetails);
		System.out.println("here ==> L");
//		JwtResponse jwtResponse = JwtResponse.builder().jwtToken(token).build();
		JwtResponse jwtResponse = new JwtResponse(token);
		return jwtResponse;
	}
	
	
}
