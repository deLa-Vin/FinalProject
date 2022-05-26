package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Status;

public interface StatusService {

	List<Status> index();
	Status show(int sid);

}
