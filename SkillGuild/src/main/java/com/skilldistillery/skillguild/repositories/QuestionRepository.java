package com.skilldistillery.skillguild.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillguild.entities.Question;

public interface QuestionRepository extends JpaRepository <Question, Integer> {

}
