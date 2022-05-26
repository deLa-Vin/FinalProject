package com.skilldistillery.skillguild.services;

import java.util.List;

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

}
