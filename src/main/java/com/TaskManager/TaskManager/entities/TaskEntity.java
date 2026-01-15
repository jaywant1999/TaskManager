package com.TaskManager.TaskManager.entities;

import java.util.Date; 

import lombok.Data;

@Data
public class TaskEntity {
	
	private int id;
	private String title;
	private String description;
	private Date deadline;
	private boolean completed;
 

}
