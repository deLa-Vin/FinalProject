package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Comment;
import com.skilldistillery.skillguild.entities.Content;
import com.skilldistillery.skillguild.entities.Guild;
import com.skilldistillery.skillguild.entities.User;
import com.skilldistillery.skillguild.repositories.GuildRepository;
import com.skilldistillery.skillguild.repositories.UserRepository;

@Service
public class GuildServiceImpl implements GuildService {

	@Autowired
	private GuildRepository guildRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Guild> index() {
		return guildRepo.findAll();
	}

	@Override
	public Guild show(int gid) {

		Optional<Guild> op = guildRepo.findById(gid);
		if (op.isPresent()) {
			Guild result = op.get();
			return result;
		}

		return null;
	}

	@Override
	public Guild create(int uid, Guild guild) {

		Optional<User> op = userRepo.findById(uid);
		if (op.isPresent()) {
			User user = op.get();
			guild.setUserCreatedBy(user);
			return guildRepo.saveAndFlush(guild);
		}

		return null;

	}

	@Override
	public Guild update(int gid, Guild guild) {

		Optional<Guild> op = guildRepo.findById(gid);
		if (op.isPresent()) {
			Guild result = op.get();
			guild.setId(gid);
			guild.setUserCreatedBy(result.getUserCreatedBy());
			return guildRepo.saveAndFlush(guild);
		}

		return null;
	}

	@Override
	public boolean delete(int gid) {

		Optional<Guild> op = guildRepo.findById(gid);
		if (op.isPresent()) {
			guildRepo.deleteById(gid);
			op = guildRepo.findById(gid);
			return !op.isPresent();

		}
		return false;
	}

}
