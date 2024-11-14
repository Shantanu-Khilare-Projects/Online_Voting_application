package com.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.LoginDTO;
import com.app.dtos.RegisterDTO;
import com.app.dtos.SigninResponse;
import com.app.entities.User;
import com.app.security.CustomUserDetails;
import com.app.security.JwtUtils;
import com.app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginDTO request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(),
				request.getPassword());
		Authentication verifiedToken = authMgr.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(verifiedToken);
		CustomUserDetails userDetail = (CustomUserDetails) verifiedToken.getPrincipal();
		User user = userDetail.getUser();
		SigninResponse resp = new SigninResponse(jwtUtils.generateJwtToken(verifiedToken), "Successful Auth!!!!", user);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody RegisterDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(dto));
	}

}
