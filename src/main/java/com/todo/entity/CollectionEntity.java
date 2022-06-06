package com.todo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "collections")
public class CollectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer collection_id;
	
	@Column(unique = true)
	private String collection_name;
	
	@Column()
	private String collection_icon;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "username" })
	private UserEntity user_fk;

	public CollectionEntity() {
		super();
	}

	public CollectionEntity(Integer collection_id, String collection_name, String collection_icon, UserEntity user_fk) {
		super();
		this.collection_id = collection_id;
		this.collection_name = collection_name;
		this.collection_icon = collection_icon;
		this.user_fk = user_fk;
	}

	public Integer getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(Integer collection_id) {
		this.collection_id = collection_id;
	}

	public String getCollection_name() {
		return collection_name;
	}

	public void setCollection_name(String collection_name) {
		this.collection_name = collection_name;
	}

	public String getCollection_icon() {
		return collection_icon;
	}

	public void setCollection_icon(String collection_icon) {
		this.collection_icon = collection_icon;
	}

	public UserEntity getUser_fk() {
		return user_fk;
	}

	public void setUser_fk(UserEntity user_fk) {
		this.user_fk = user_fk;
	}
	
	
	
}
