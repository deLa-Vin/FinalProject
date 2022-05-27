package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.skillguild.entities.Question;
import com.skilldistillery.skillguild.entities.Status;
import com.skilldistillery.skillguild.repositories.QuestionRepository;


public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	@Override
	public List<Question> index() {
		return questionRepo.findAll();
	}
	
	@Override
	public Question show(int qid) {

		Optional<Question> op = questionRepo.findById(qid);
		if (op.isPresent()) {
			Question result = op.get();
			return result;
		}

		return null;
	}

	
	
	
}