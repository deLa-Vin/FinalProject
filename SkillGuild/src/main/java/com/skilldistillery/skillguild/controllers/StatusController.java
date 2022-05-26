package com.skilldistillery.skillguild.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillguild.entities.Status;
import com.skilldistillery.skillguild.services.StatusService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("v1")
public class StatusController {

	@Autowired
	StatusService statusServ;

	@GetMapping("statuses")
	public List<Status> index() {
		return statusServ.index();
	}

}
