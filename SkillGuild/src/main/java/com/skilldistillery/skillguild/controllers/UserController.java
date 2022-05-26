package com.skilldistillery.skillguild.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillguild.entities.User;
import com.skilldistillery.skillguild.services.UserService;

@RestController
@RequestMapping("v1")
@CrossOrigin({ "*", "http://localhost:4202" })
public class UserController {

	@Autowired
	private UserService userSvc;

	@GetMapping("users")
	public List<User> index() {
		return userSvc.index();
	}

	@GetMapping("users/{userId}")
	private User getById(@PathVariable int userId, HttpServletRequest req, HttpServletResponse res) {
		User user = userSvc.getUserById(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	@DeleteMapping("users/{userId}")
	public boolean deleteUser(
			@PathVariable Integer userId,
			HttpServletResponse res
			) {
				
		if (userSvc.deleteUser(userId)) {
			res.setStatus(204);
			return true;
	}
	else {
		res.setStatus(404);
		return false;
	}

 }
	
}
	
//	// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
//	@GetMapping("test/users/{userId}")
//	public User getUserForTest(
//	  @PathVariable Integer userId,
//	  HttpServletResponse res
//	) {
//	  User user = userSvc.getUserById(userId);
//	  if (user == null) {
//	    res.setStatus(404);
//	  }
//	  return user;
//	}

	// start


