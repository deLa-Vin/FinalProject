package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Status;
import com.skilldistillery.skillguild.repositories.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	StatusRepository statusRepo;

	@Override
	public List<Status> index() {
		return statusRepo.findAll();
	}
	
	@Override
	public Status show(int sid) {

		Optional<Status> op = statusRepo.findById(sid);
		if (op.isPresent()) {
			Status result = op.get();
			return result;
		}

		return null;
	}

}
