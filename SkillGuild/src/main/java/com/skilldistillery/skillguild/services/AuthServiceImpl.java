package com.skilldistillery.skillguild.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.User;
import com.skilldistillery.skillguild.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public User register(User user) {
		// user.setAboutMe(user.getAboutMe());
		user.setEmail(user.getEmail());
		user.setEnabled(user.isEnabled());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setPassword(encoder.encode(user.getPassword()));
		user.setProfileImgUrl(user.getProfileImgUrl());
		user.setRole("standard");
		user.setUsername(user.getUsername());
		
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
