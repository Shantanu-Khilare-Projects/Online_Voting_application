package com.app.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PollUpdateDTO {

	private String statement;
	private LocalDate LastDate;
	private boolean active;
}
