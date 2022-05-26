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

import com.skilldistillery.skillguild.entities.Content;
import com.skilldistillery.skillguild.entities.Guild;
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

	@GetMapping("contents/{cid}")
	public Content show(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid) {

		Content content = contentServ.show(cid);

		if (content == null) {
			res.setStatus(404);
		}

		return content;
	}

}
