package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.dtos.PollDTO;
import com.app.dtos.PollUpdateDTO;
import com.app.entities.Poll;

public interface PollService {

	ApiResponse addPoll(Long userId, PollDTO dto);

	ApiResponse endPoll(Long pollId);

	ApiResponse updatePoll(Long pollId, PollUpdateDTO dto);

	List<Poll> getAllPolls();

	Poll getPollById(Long pollId);
}
