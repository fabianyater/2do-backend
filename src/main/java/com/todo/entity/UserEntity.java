package com.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column()
	private String user_fullname;
	
	@Column()
	private String user_avatar;
	
	@Column(unique = true)
	private String username;
	
	@Column()
	private String user_password;
	
	@Column
	private String jwt;

	public UserEntity(String username, String jwt) {
		super();
		this.username = username;
		this.jwt = jwt;
	}

	public UserEntity() {
		
	}

	public UserEntity(Integer user_id, String user_fullname, String user_avatar, String username,
			String user_password) {
		super();
		this.user_id = user_id;
		this.user_fullname = user_fullname;
		this.user_avatar = user_avatar;
		this.username = username;
		this.user_password = user_password;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_fullname() {
		return user_fullname;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
}
