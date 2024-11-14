package com.app.services;

import com.app.dtos.ApiResponse;
import com.app.dtos.LoginDTO;
import com.app.dtos.RegisterDTO;

public interface UserService {

	ApiResponse registerUser(RegisterDTO dto);
	ApiResponse loginUser(LoginDTO dto);
}
