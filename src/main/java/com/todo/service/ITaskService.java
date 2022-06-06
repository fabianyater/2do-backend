package com.todo.service;

import java.util.List;

import com.todo.entity.CollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.entity.TaskEntity;
import com.todo.repository.TaskRepository;

@Service
public class ITaskService implements TaskService{
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public TaskEntity saveTask(TaskEntity task) throws Exception {
		return taskRepository.save(task);
	}

	@Override
	public List<TaskEntity> getAllTasks() throws Exception {
		return taskRepository.findAll();
	}

	@Override
	public TaskEntity getTaskById(int id) throws Exception {
		return taskRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteTaskById(int id) throws Exception {
		taskRepository.deleteById(id);
	}
}
