package com.skilldistillery.skillguild.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillguild.entities.Content;
import com.skilldistillery.skillguild.services.ContentService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("v1")
public class ContentController {

	@Autowired
	ContentService contentServ;

	@GetMapping("contents")
	public List<Content> index() {
		return contentServ.index();
	}

}
