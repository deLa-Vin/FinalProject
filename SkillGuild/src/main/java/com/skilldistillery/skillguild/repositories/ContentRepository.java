package com.skilldistillery.skillguild.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillguild.entities.Content;

public interface ContentRepository extends JpaRepository<Content, Integer> {

}
