package com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.entity.UserEntity;
import com.todo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IUserService implements UserService, UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserEntity getUserById(int id) throws Exception {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		return new User(user.getUsername(), user.getUser_password(), new ArrayList<>());
	}

	@Override
	public UserEntity saveUser(UserEntity user) throws Exception {
		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> getLoggedUser() throws Exception {
		return userRepository.findAll();
	}

	@Override
	public void deleteUserById(int id) throws Exception {
		userRepository.deleteById(id);
	}
}
