package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUsername(String username);
}
