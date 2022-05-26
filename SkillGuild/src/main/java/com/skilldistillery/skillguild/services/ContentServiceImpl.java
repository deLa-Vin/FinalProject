package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Content;
import com.skilldistillery.skillguild.entities.Guild;
import com.skilldistillery.skillguild.entities.Status;
import com.skilldistillery.skillguild.entities.User;
import com.skilldistillery.skillguild.repositories.ContentRepository;
import com.skilldistillery.skillguild.repositories.GuildRepository;
import com.skilldistillery.skillguild.repositories.StatusRepository;
import com.skilldistillery.skillguild.repositories.UserRepository;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	ContentRepository contentRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	GuildRepository guildRepo;

	@Autowired
	StatusRepository statusRepo;

	@Override
	public List<Content> index() {
		return contentRepo.findAll();
	}

	@Override
	public Content show(int cid) {

		Optional<Content> op = contentRepo.findById(cid);
		if (op.isPresent()) {
			Content result = op.get();
			return result;
		}

		return null;
	}

	@Override
	public Content create(int uid, int gid, int sid, Content content) {

		Optional<User> userOp = userRepo.findById(uid);
		if (userOp.isPresent()) {
			User user = userOp.get();
			content.setUserCreatedContent(user);

			Optional<Guild> guildOp = guildRepo.findById(gid);
			if (guildOp.isPresent()) {
				Guild guild = guildOp.get();
				content.setGuild(guild);

				Optional<Status> statusOp = statusRepo.findById(sid);
				if (statusOp.isPresent()) {
					Status status = statusOp.get();
					content.setStatus(status);

				}

				return contentRepo.saveAndFlush(content);
			}
		}

		return null;
	}

	@Override
	public Content update(int cid, Content content) {

		Optional<Content> op = contentRepo.findById(cid);
		if (op.isPresent()) {
			Content result = op.get();
			content.setId(cid);
			content.setUserCreatedContent(result.getUserCreatedContent());
			content.setGuild(result.getGuild());
			content.setStatus(result.getStatus());
			return contentRepo.saveAndFlush(content);
		}

		return null;
	}

	@Override
	public boolean delete(int cid) {

		Optional<Content> op = contentRepo.findById(cid);
		if (op.isPresent()) {
			Content result = op.get();
			contentRepo.deleteById(cid);
			op = contentRepo.findById(cid);
			return !op.isPresent();

		}
		return false;
	}

}
