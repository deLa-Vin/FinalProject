package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.User;

public interface UserService {

	User getUserById(int userId);

	List<User> index();

	boolean deleteUser(int userId);

}
