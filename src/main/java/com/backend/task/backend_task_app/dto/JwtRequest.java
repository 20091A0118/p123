package com.backend.task.backend_task_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequest {
	
	private String username;
	private String password;

}
