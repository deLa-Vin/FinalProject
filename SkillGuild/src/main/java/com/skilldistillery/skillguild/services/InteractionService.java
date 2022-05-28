package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Content;
import com.skilldistillery.skillguild.entities.Interaction;

public interface InteractionService {
	
	List<Interaction> index();
	Interaction show(int iid);
	Interaction create(int uid, int contentId, Interaction interaction);
	Interaction update(int iid, Interaction interaction);
	boolean delete(int iid);
	List<Interaction> showUserInteractions(int uid);
	List<Interaction> showContentInteractions(int cid);

}
