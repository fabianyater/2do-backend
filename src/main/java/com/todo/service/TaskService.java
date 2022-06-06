package com.todo.service;

import java.util.List;

import com.todo.entity.CollectionEntity;
import com.todo.entity.TaskEntity;

public interface TaskService {

	public TaskEntity saveTask(TaskEntity task) throws Exception;

	public List<TaskEntity> getAllTasks() throws Exception;

	public TaskEntity getTaskById(int id) throws Exception;

	public void deleteTaskById(int id) throws Exception;
	
}
