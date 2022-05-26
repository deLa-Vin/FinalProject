package com.skilldistillery.skillguild.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillguild.entities.Resource;
import com.skilldistillery.skillguild.services.ResourceService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("v1")
public class ResourceController {

	@Autowired
	ResourceService resourceServ;

	@GetMapping("resources")
	public List<Resource> index() {
		return resourceServ.index();
	}

	@GetMapping("resources/{rid}")
	public Resource show(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid) {

		Resource resource = resourceServ.show(rid);

		if (resource == null) {
			res.setStatus(404);
		}

		return resource;
	}

	@PostMapping("resourcetype/{rtid}/resources")
	public Resource create(HttpServletRequest req, HttpServletResponse res, @PathVariable int rtid,
			@RequestBody Resource resource) {

		try {

			if (resourceServ.create(rtid, resource) == null) {
				res.setStatus(400);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL().append("/").append(resource.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);

		}

		return resource;

	}

	@DeleteMapping("resources/{rid}")
	public void delete(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid) {

		try {
			if (resourceServ.delete(rid)) {

				res.setStatus(204);

			} else {

				res.setStatus(404);

			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}

	}

}
