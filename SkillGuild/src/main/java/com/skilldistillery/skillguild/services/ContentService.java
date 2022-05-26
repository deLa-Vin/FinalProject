package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Content;

public interface ContentService {

	List<Content> index();

	Content show(int cid);

	Content create(int uid, int gid, int sid, Content content);
}
