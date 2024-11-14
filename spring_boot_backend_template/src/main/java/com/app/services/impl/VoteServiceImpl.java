package com.app.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.OptionDao;
import com.app.daos.PollDao;
import com.app.daos.UserDao;
import com.app.daos.VoteDao;
import com.app.dtos.ApiResponse;
import com.app.entities.Option;
import com.app.entities.Poll;
import com.app.entities.Role;
import com.app.entities.User;
import com.app.entities.Vote;
import com.app.services.VoteService;

@Service
@Transactional
public class VoteServiceImpl implements VoteService{

	@Autowired
	private VoteDao voteDao;
	@Autowired
	private PollDao pollDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OptionDao optionDao;
		
	@Override
	public ApiResponse vote(Long userId, Long pollId, Long optionId) {
		Poll poll= pollDao.findById(pollId)
				.orElseThrow(()->new RuntimeException("Poll not found..."));
		User user=userDao.findById(userId)
				.orElseThrow(()->new RuntimeException("Invalid user ID"));
		Option option=optionDao.findById(optionId)
				.orElseThrow(()->new RuntimeException("Invalid option ID"));

		if(!voteDao.existsByUserAndPoll(user, poll) & user.getRole()==Role.ROLE_VOTER) {
			Vote vote=new Vote();
			vote.setOption(option);
			vote.setPoll(poll);
			vote.setUser(user);
			
			option.setVoteCount(option.getVoteCount()+1);
			optionDao.save(option);
			
			voteDao.save(vote);
			return new ApiResponse("Vote Registered. Thankyou for your contribution to the betterment of the nation!");
		}
		if(user.getRole()!=Role.ROLE_VOTER)
			return new ApiResponse("Only verified voters can cast their votes...");
		
		return new ApiResponse("Sorry. you have already voted...");
	}

}
