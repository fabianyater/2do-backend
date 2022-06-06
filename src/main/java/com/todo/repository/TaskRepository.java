package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

}
