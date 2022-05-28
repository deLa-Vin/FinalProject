package com.skilldistillery.skillguild.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillguild.entities.Question;
import com.skilldistillery.skillguild.services.QuestionService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("v1")
public class QuestionController {

	@Autowired
	QuestionService questionServ;

	@GetMapping("questiones")
	public List<Question> index() {
		return questionServ.index();
	}
	
	@GetMapping("questiones/{sid}")
	public Question show(HttpServletRequest req, HttpServletResponse res, @PathVariable int sid) {

		Question question = questionServ.show(sid);

		if (question == null) {
			res.setStatus(404);
		}

		return question;
	}

}
