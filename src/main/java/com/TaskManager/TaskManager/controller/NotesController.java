package com.TaskManager.TaskManager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManager.TaskManager.dto.CreateNoteDTO;
import com.TaskManager.TaskManager.dto.CreateNoteResponseDTO;
import com.TaskManager.TaskManager.entities.NoteEntity;
import com.TaskManager.TaskManager.services.NoteService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
	
	private NoteService noteService;
	
	public NotesController(NoteService noteService) {
		 this.noteService = noteService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
		var notes = noteService.getNotesForTasks(taskId);
		
		return ResponseEntity.ok(notes);		
	}
	
	@PostMapping("")
	public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body){
		var note = noteService.addNoteForTask(taskId, body.getTitle(),body.getBody());
		
		return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));
	}

}
