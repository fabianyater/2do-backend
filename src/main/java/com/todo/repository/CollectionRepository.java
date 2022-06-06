package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.CollectionEntity;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Integer> {

}
