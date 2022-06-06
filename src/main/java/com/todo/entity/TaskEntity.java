package com.todo.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "tasks")
public class TaskEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer task_id;
	
	@Column()
	private String task_title;
	
	@Column()
	private String task_comment;
	
	@Column(columnDefinition = "DATE")
	private Date task_due_date;
	
	@Column(columnDefinition = "TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp task_reminder;
	
	@Column()
	private Integer task_state;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "collection_name" })
	private CollectionEntity collection_fk;

	public TaskEntity() {
		super();
	}

	public TaskEntity(Integer task_id, String task_title, String task_comment, Date task_due_date,
			Timestamp task_reminder, Integer task_state, CollectionEntity collection_fk) {
		super();
		this.task_id = task_id;
		this.task_title = task_title;
		this.task_comment = task_comment;
		this.task_due_date = task_due_date;
		this.task_reminder = task_reminder;
		this.task_state = task_state;
		this.collection_fk = collection_fk;
	}

	public Integer getTask_id() {
		return task_id;
	}

	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}

	public String getTask_title() {
		return task_title;
	}

	public void setTask_title(String task_title) {
		this.task_title = task_title;
	}

	public String getTask_comment() {
		return task_comment;
	}

	public void setTask_comment(String task_comment) {
		this.task_comment = task_comment;
	}

	public Date getTask_due_date() {
		return task_due_date;
	}

	public void setTask_due_date(Date task_due_date) {
		this.task_due_date = task_due_date;
	}

	public Timestamp getTask_reminder() {
		return task_reminder;
	}

	public void setTask_reminder(Timestamp task_reminder) {
		this.task_reminder = task_reminder;
	}

	public Integer getTask_state() {
		return task_state;
	}

	public void setTask_state(Integer task_state) {
		this.task_state = task_state;
	}

	public CollectionEntity getCollection_fk() {
		return collection_fk;
	}

	public void setCollection_fk(CollectionEntity collection_fk) {
		this.collection_fk = collection_fk;
	}
		
}
