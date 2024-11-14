package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.VoteService;


@RestController
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;

	@PostMapping("/voter/castVote/{userId}/{pollId}/{optionId}")
	public ResponseEntity<?> vote(@RequestParam Long userId,
									@RequestParam Long pollId, @RequestParam Long optionId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(voteService.vote(userId, pollId, optionId));
		
	}
	
}
