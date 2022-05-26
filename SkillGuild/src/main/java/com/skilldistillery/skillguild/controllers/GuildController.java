package com.skilldistillery.skillguild.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillguild.entities.Guild;
import com.skilldistillery.skillguild.services.GuildService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
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

	@PostMapping("users/{uid}/guilds")
	public Guild create(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @RequestBody Guild guild) {

		try {
			guildServ.create(uid, guild);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL().append("/").append(guild.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);

		}

		return guild;

	}
}
