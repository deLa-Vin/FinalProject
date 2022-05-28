package com.skilldistillery.skillguild.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillguild.entities.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Integer>{

//	List<Interaction> findByUserId(int uid);

//	List<Interaction> findbyContentId(int cid);

}
