package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.OptionDTO;
import com.app.services.OptionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/option")
public class OptionController {

	@Autowired
	private OptionService optionService;
	
	@PostMapping("/admin/add/{pollId}")		
	public ResponseEntity<?> addOption(@RequestParam Long pollId, @RequestBody OptionDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(optionService.addOption(pollId, dto));
	}
	
}
