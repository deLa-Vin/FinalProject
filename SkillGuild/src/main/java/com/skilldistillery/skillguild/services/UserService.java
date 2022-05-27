package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.User;

public interface UserService {

	List<User> index();

	User show(int uid);

	User create(User user);

	boolean deleteUser(int userId);

	User update(int uid, User user);

}
