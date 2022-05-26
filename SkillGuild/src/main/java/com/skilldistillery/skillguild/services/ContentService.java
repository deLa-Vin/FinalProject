package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Content;

public interface ContentService {

	List<Content> index();
	
	List<Content> guildContents(int gid);

	Content show(int cid);

	Content create(int uid, int gid, int sid, Content content);
	
	Content update(int cid, Content content);
	
	boolean delete(int cid);
	
	Content showGuildContent(int gid, int cid);
}
