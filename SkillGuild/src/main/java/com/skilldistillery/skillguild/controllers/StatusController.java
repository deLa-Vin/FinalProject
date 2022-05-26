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
	
	@GetMapping("statuses/{sid}")
	public Status show(HttpServletRequest req, HttpServletResponse res, @PathVariable int sid) {

		Status status = statusServ.show(sid);

		if (status == null) {
			res.setStatus(404);
		}

		return status;
	}

}
