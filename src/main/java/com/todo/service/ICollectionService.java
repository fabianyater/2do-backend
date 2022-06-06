package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.entity.CollectionEntity;
import com.todo.repository.CollectionRepository;

@Service
public class ICollectionService implements CollectionService {
	
	@Autowired
	CollectionRepository collectionRepository;


	@Override
	public CollectionEntity saveCollection(CollectionEntity collection) throws Exception {
		return collectionRepository.save(collection);
	}

	@Override
	public List<CollectionEntity> getAllCollections() throws Exception {
		return collectionRepository.findAll();
	}

	@Override
	public CollectionEntity getCollectionById(int id) throws Exception {
		return collectionRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCollectionById(int id) throws Exception {
		collectionRepository.deleteById(id);
	}
}
