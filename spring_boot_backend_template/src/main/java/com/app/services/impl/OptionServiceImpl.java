package com.app.services.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.OptionDao;
import com.app.daos.PollDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.OptionDTO;
import com.app.entities.Option;
import com.app.entities.Poll;
import com.app.services.OptionService;

@Service
@Transactional
public class OptionServiceImpl implements OptionService{

	@Autowired
	private PollDao pollDao;
	
	@Autowired
	private OptionDao optionDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addOption(Long pollId, OptionDTO dto) {
		Poll poll= pollDao.findById(pollId)
							.orElseThrow(()->new RuntimeException("Poll not found..."));
		
		Option option=mapper.map(dto, Option.class);
		option.setVoteCount(0);
		option.setPoll(poll);
		optionDao.save(option);
		return new ApiResponse("Option added for poll "+poll.getStatement());
	}

}
