package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Content;
import com.skilldistillery.skillguild.entities.Question;
import com.skilldistillery.skillguild.repositories.ContentRepository;
import com.skilldistillery.skillguild.repositories.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	private ContentRepository contentRepo;
	
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
	public Question create(int uid, Question question) {

		Optional<Content> co = contentRepo.findById(uid);
		if (co.isPresent()) {
			Content content = co.get();
			question.setContent(content);
			return questionRepo.saveAndFlush(question);
		}

		return null;
	}

	@Override
	public Question update(int qid, Question question) {
		Optional<Question> co = questionRepo.findById(qid);
		if (co.isPresent()) {
			Question result = co.get();
			question.setId(qid);
			question.setContent(result.getContent());
			return questionRepo.saveAndFlush(question);
		}

		return null;
		
	}

	@Override
	public boolean delete(int qid) {

		Optional<Question> qu = questionRepo.findById(qid);
		if (qu.isPresent()) {
			questionRepo.deleteById(qid);
			qu = questionRepo.findById(qid);
			return !qu.isPresent();

		}
		return false;
		
	
	}
	
	
}