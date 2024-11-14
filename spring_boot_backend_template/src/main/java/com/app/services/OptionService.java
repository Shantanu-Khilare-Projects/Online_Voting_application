package com.app.services;

import com.app.dtos.ApiResponse;
import com.app.dtos.OptionDTO;

public interface OptionService {

	public ApiResponse addOption(Long pollId, OptionDTO dto);
	
}
