package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Guild;

public interface GuildService {
	
	List<Guild> index();
	List<Guild> memberOfGuild(String username);
	Guild show(int gid);
	Guild create(int uid, Guild guild);
	Guild update(int gid, Guild guild);
	boolean delete(int gid);
	boolean join(int gid, int uid);

}
