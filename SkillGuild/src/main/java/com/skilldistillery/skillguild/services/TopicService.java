package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Topic;
import com.skilldistillery.skillguild.entities.User;

public interface TopicService {

	List<Topic> index();

	Topic show(int tid);

	Topic create(Topic topic);
	
	boolean delete(int topicId);

	Topic update(int tid, Topic topic);
}
