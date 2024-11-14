package com.app.services.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.LoginDTO;
import com.app.dtos.RegisterDTO;
import com.app.entities.User;
import com.app.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;

	@Override
	public ApiResponse registerUser(RegisterDTO dto) {
		User user=mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
		return new ApiResponse("User Registered");
		
	}

	@Override
	public ApiResponse loginUser(LoginDTO dto) {
		User user=mapper.map(dto, User.class);
		if(user.getUsername()==dto.getUsername() &&
				user.getPassword() == dto.getPassword())
			return new ApiResponse("Logged in as "+dto.getUsername());
		
		else
			return new ApiResponse("Invalid Credentials");
	}

}
