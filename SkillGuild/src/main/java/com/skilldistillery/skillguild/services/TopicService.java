package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Topic;

public interface TopicService {

	List<Topic> index();

	Topic show(int tid);

	Topic create(Topic topic);
}
