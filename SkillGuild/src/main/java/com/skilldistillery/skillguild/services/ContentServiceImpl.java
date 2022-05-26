package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Content;
import com.skilldistillery.skillguild.repositories.ContentRepository;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	ContentRepository contentRepo;

	@Override
	public List<Content> index() {
		return contentRepo.findAll();
	}

	@Override
	public Content show(int cid) {

		Optional<Content> op = contentRepo.findById(cid);
		if (op.isPresent()) {
			Content result = op.get();
			return result;
		}

		return null;
	}

}
