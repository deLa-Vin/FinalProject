package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Question;

public interface QuestionService {

	List<Question> index();
	Question show(int qid);

}
