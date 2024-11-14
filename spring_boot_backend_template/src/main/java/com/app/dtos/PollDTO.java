package com.app.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PollDTO {

	private String statement;
	private LocalDate LastDate;
}
