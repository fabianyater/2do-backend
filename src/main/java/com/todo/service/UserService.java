package com.todo.service;

import java.util.List;

import com.todo.entity.UserEntity;


public interface UserService {
	
	public UserEntity saveUser(UserEntity user) throws Exception;
	
	public UserEntity getUserById(int id) throws Exception;
	
	public List<UserEntity> getLoggedUser() throws Exception;

	public void deleteUserById(int id) throws Exception;
	
}
