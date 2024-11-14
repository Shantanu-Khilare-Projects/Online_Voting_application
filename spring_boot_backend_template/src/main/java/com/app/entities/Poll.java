package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "polls")
public class Poll extends BaseEntity{

	private String statement;
	
	@Column(columnDefinition = "TINYINT(1)")
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private User user;
	
	@Column(name = "expiration_date")
	private LocalDate LastDate;
	
}
