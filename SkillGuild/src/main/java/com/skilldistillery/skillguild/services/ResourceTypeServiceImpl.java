package com.skilldistillery.skillguild.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillguild.entities.ResourceType;
import com.skilldistillery.skillguild.repositories.ResourceTypeRepository;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

	@Autowired
	ResourceTypeRepository resourceTypeRepo;

	@Override
	public List<ResourceType> index() {
		return resourceTypeRepo.findAll();
	}

}
