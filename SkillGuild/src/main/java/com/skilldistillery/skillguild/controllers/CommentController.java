package com.skilldistillery.skillguild.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillguild.entities.Comment;
import com.skilldistillery.skillguild.services.CommentService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("v1")
public class CommentController {

	@Autowired
	CommentService commentServ;

	@GetMapping("comments")
	public List<Comment> index() {
		return commentServ.index();
	}

}
