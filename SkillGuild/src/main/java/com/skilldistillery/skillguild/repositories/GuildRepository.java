package com.skilldistillery.skillguild.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillguild.entities.Guild;

public interface GuildRepository extends JpaRepository<Guild, Integer> {

	
	
}
