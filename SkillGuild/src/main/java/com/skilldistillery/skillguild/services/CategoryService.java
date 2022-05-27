package com.skilldistillery.skillguild.services;

import java.util.List;

import com.skilldistillery.skillguild.entities.Category;

public interface CategoryService {

	List<Category> index();
	Category show(int cid);

}
