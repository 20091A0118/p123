package com.backend.task.backend_task_app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.task.backend_task_app.jwt.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	//The basic default authentication provider is SpringBootWebSecurityConfiguration.class
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("here======A");
		http.csrf(csrf ->csrf.disable());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/task/users/register","/auth/login").permitAll()
				.anyRequest().authenticated());
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		//we can assign multiple roles here, which do not have credentials
//		http.formLogin(withDefaults());
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		System.out.println("here======E");
//		http.httpBasic(withDefaults());
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticate(AuthenticationConfiguration authentication)throws Exception{
		System.out.println("here======B");
		
		return authentication.getAuthenticationManager();
	}
	
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user=User.builder()
//				             .username("priya")
//				             .password(encoder().encode("priya123"))
//				             .roles("NORMAL")
//				             .build();
//		UserDetails admin=User.builder()
//	                          .username("ajay")
//	                          .password(encoder().encode("ajay123"))
//	                          .roles("ADMIN")
//	                          .build();
//		
//		return new InMemoryUserDetailsManager(user,admin);				
//	}
	@Bean
	public PasswordEncoder encoder() {
		System.out.println("here======C");
		return new BCryptPasswordEncoder();
	}

}

