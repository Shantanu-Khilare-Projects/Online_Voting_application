package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "options")
public class Option extends BaseEntity{

	private String name;
	private int voteCount;
	
	@ManyToOne
	@JoinColumn(name = "poll_id")
	private Poll poll;
}
