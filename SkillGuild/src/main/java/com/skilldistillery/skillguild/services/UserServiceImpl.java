package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.User;
import com.skilldistillery.skillguild.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User show(int userId, String username) {

		Optional<User> userOpt = userRepo.findById(userId);

		boolean isAdmin = userRepo.findByUsername(username).getRole().equals("data_admin");

		System.err.println(userOpt.isPresent());

		if (userOpt.isPresent()) {

			User user = userOpt.get();

			if (user.getUsername().equals(username) || isAdmin) {

				return user;

			}

		}

		return null;

	}

	@Override
	public User showProfile(String username) {

		User user = userRepo.findByUsername(username);

		if (user != null) {

			return user;

		}

		return null;

	}

	@Override
	public boolean deleteUser(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			userRepo.delete(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User update(int uid, User user) {
		Optional<User> op = userRepo.findById(uid);
		if (op.isPresent()) {
			User result = op.get();
			result = user;
			result.setId(uid);
			return userRepo.saveAndFlush(result);
		}
		return null;
	}

}
