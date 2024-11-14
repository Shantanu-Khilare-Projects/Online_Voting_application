package com.app.dtos;

import com.app.entities.Role;

import lombok.Data;

@Data
public class RegisterDTO {

	private String username;
	private String password;
	private Role role;
	
}
