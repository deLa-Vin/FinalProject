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

import com.skilldistillery.skillguild.entities.Guild;
import com.skilldistillery.skillguild.services.GuildService;

@RestController
@CrossOrigin({"*", "http://localhost"})
@RequestMapping("v1")
public class GuildController {
	
	@Autowired
	GuildService guildServ;

	@GetMapping("guilds")
	public List<Guild> index() {
		return guildServ.index();
	}
	
	@GetMapping("guilds/{gid}")
	public Guild show(HttpServletRequest req, HttpServletResponse res, @PathVariable int gid) {

		Guild guild = guildServ.show(gid);

		if (guild == null) {
			res.setStatus(404);
		}

		return guild;
	}
}
