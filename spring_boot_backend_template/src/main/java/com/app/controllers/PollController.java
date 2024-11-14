package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.PollDTO;
import com.app.dtos.PollUpdateDTO;
import com.app.services.PollService;

@RestController
@RequestMapping("/poll")
public class PollController {
	
	@Autowired
	private PollService pollService;
	
	@PostMapping("/admin/{userId}")
	public ResponseEntity<?> addNewPoll(@RequestParam Long userId, @RequestBody PollDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pollService.addPoll(userId, dto));
	}
	
	@PutMapping("/admin/end/{id}")
	public ResponseEntity<?> endPoll(@PathVariable Long id) {		
		return ResponseEntity.ok(pollService.endPoll(id));
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> updatePoll(@PathVariable Long id, @RequestBody PollUpdateDTO dto) {		
		return ResponseEntity.ok(pollService.updatePoll(id, dto));
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllPolls() {
		return ResponseEntity.ok(pollService.getAllPolls());
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<?> getPollById(@RequestParam Long id) {
		return ResponseEntity.ok(pollService.getPollById(id));
	}
	
	
}
