package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Comment;

public interface CommentService {
	
	List<Comment> index();
	Comment show(int cid);
	Comment create(int uid, int contentId, Comment comment);
	Comment update(int cid, Comment comment);
	boolean delete(int cid);

}
