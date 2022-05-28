package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.User;
import com.skilldistillery.skillguild.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User show(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}
		return null;
	}

//	@Override
//	public User create(User newUser) {
//		newUser.setPassword(encoder.encode(newUser.getPassword()));
//		return userRepo.saveAndFlush(newUser);
//	}

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
