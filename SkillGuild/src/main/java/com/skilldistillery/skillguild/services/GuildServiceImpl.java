package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Guild;
import com.skilldistillery.skillguild.repositories.GuildRepository;

@Service
public class GuildServiceImpl implements GuildService {

	@Autowired
	private GuildRepository guildRepo;

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

}
