package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Option;

public interface OptionDao extends JpaRepository<Option, Long>{

}
