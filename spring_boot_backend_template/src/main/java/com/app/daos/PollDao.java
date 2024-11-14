package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Poll;

public interface PollDao extends JpaRepository<Poll, Long>{

	public List<Poll> findByActive(boolean active);
}
