package com.skilldistillery.skillguild.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Comment;
import com.skilldistillery.skillguild.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepo;

	@Override
	public List<Comment> index() {
		return commentRepo.findAll();
	}
	
	

}
