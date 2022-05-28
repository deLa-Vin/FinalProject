package com.skilldistillery.skillguild.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.Category;
import com.skilldistillery.skillguild.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Override
	public List<Category> index() {
		return categoryRepo.findAll();
	}
	
	@Override
	public Category show(int qid) {

		Optional<Category> op = categoryRepo.findById(qid);
		if (op.isPresent()) {
			Category result = op.get();
			return result;
		}

		return null;
	}

}