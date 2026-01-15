package com.TaskManager.TaskManager.dto;

import java.util.Date;
import java.util.List;

import com.TaskManager.TaskManager.entities.NoteEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
	private int id;
	private String title;
	private String description;
	private Date deadline;
	private boolean completed;
	private List<NoteEntity> notes;
}
