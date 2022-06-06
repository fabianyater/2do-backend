package com.todo.service;

import com.todo.entity.CollectionEntity;

import java.util.List;

public interface CollectionService {

    public CollectionEntity saveCollection(CollectionEntity collection) throws Exception;

    public List<CollectionEntity> getAllCollections() throws Exception;

    public CollectionEntity getCollectionById(int id) throws Exception;

	public void deleteCollectionById(int id) throws Exception;

}
