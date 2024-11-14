package com.app.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.PollDao;
import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.PollDTO;
import com.app.dtos.PollUpdateDTO;
import com.app.entities.Poll;
import com.app.entities.User;
import com.app.services.PollService;

@Service
@Transactional
public class PollServiceImpl implements PollService{

	
	@Autowired
	private PollDao pollDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addPoll(Long userId, PollDTO dto) {

		if(dto.getLastDate().compareTo(LocalDate.now()) < 0) {
			return new ApiResponse("Last Date is already passed...");
		}
		User user=userDao.findById(userId)
							.orElseThrow(()->new RuntimeException("Invalid user ID"));
		
		Poll poll=mapper.map(dto, Poll.class);
		poll.setUser(user);
		poll.setActive(true);
		
		pollDao.save(poll);
		return new ApiResponse("poll added and is now active");
	}

	@Override
	public ApiResponse endPoll(Long pollId) {
		Poll poll=pollDao.findById(pollId)
				.orElseThrow(()->new RuntimeException("Invalid poll ID"));
		poll.setActive(false);
		pollDao.save(poll);
		return new ApiResponse("Poll ended successfully!!!");
	}

	@Override
	public ApiResponse updatePoll(Long pollId, PollUpdateDTO dto) {
		if(dto.getLastDate().compareTo(LocalDate.now()) < 0) {
			return new ApiResponse("Last Date is already passed...");
		}
		Poll poll=pollDao.findById(pollId)
				.orElseThrow(()->new RuntimeException("Invalid poll ID"));
		mapper.map(dto, poll);
		pollDao.save(poll);
		return new ApiResponse("Poll updated successfully!!!");
	}

	@Override
	public List<Poll> getAllPolls() {
		return pollDao.findByActive(true);
	}

	@Override
	public Poll getPollById(Long pollId) {
		return pollDao.findById(pollId)
				.orElseThrow(()->new RuntimeException("Invalid poll ID"));
	}

}
