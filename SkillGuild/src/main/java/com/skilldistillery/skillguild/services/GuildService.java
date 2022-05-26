package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Guild;

public interface GuildService {
	
	public List<Guild> index();
	public Guild show(int gid);
	public Guild create(int uid, Guild guild);

}
