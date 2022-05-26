package com.skilldistillery.skillguild.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillguild.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
