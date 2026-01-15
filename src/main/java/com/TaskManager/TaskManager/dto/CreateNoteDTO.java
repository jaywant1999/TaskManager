package com.TaskManager.TaskManager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateNoteDTO {

	private String title;
	private String body;
}
