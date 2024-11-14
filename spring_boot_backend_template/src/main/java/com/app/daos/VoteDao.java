package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Vote;
import com.app.entities.Poll;
import com.app.entities.User;


public interface VoteDao extends JpaRepository<Vote, Long>{

	boolean existsByUserAndPoll(User user, Poll poll);
}
