package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Question;
import com.skilldistillery.skillguild.repositories.QuestionRepository;

@Service
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

	@Override
	public Question create(Question question) {
		return questionRepo.saveAndFlush(question);
	}

	@Override
	public Question update(int qid, Question question) {
		Optional<Question> op = questionRepo.findById(qid);
		if (op.isPresent()) {
			Question result = op.get();
			question.setId(qid);
			question.setContent(result.getContent());
			return questionRepo.saveAndFlush(question);
		}
		return null;
	}

	@Override
	public boolean delete(int qid) {
		Optional<Question> opt = questionRepo.findById(qid);
		if (opt.isPresent()) {
			Question question = opt.get();
			questionRepo.delete(question);
			return true;
		} else {
			return false;
		}
	}

}