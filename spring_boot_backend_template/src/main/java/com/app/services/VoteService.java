package com.app.services;

import com.app.dtos.ApiResponse;

public interface VoteService {

	ApiResponse vote(Long userId,Long pollId, Long optionId);
}
