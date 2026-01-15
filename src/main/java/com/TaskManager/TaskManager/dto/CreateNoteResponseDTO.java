 package com.TaskManager.TaskManager.dto;

import com.TaskManager.TaskManager.entities.NoteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@AllArgsConstructor
@NoArgsConstructor 
@Getter
@Setter
public class CreateNoteResponseDTO {

	private Integer taskId;
	private NoteEntity note;
}
