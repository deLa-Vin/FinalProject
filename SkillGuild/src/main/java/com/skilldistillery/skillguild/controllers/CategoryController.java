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

import com.skilldistillery.skillguild.entities.Category;
import com.skilldistillery.skillguild.services.CategoryService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("v1")
public class CategoryController {

	@Autowired
	CategoryService catServ;

	@GetMapping("categories")
	public List<Category> index() {
		return catServ.index();
	}
	
	@GetMapping("categories/{cid}")
	public Category show(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid) {

		Category category = catServ.show(cid);

		if (category == null) {
			res.setStatus(404);
		}

		return category;
	}

}
