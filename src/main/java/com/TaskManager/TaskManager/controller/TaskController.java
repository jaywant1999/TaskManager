package com.TaskManager.TaskManager.controller;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManager.TaskManager.dto.CreateTaskDTO;
import com.TaskManager.TaskManager.dto.ErrorResponseDTO;
import com.TaskManager.TaskManager.dto.TaskResponseDTO;
import com.TaskManager.TaskManager.dto.UpdateTaskDTO;

import com.TaskManager.TaskManager.entities.TaskEntity;
import com.TaskManager.TaskManager.services.NoteService;
import com.TaskManager.TaskManager.services.TaskService;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private final TaskService taskService;
	private final NoteService noteService;
	private ModelMapper modelMapper= new ModelMapper();

	public TaskController(TaskService taskService, NoteService noteService) {
		this.taskService = taskService;
		this.noteService = noteService;
	}

	@GetMapping("")
	public ResponseEntity<List<TaskEntity>> getTasks() {
		var task = taskService.getTasks();
		
		if (task == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(task);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id) {
		var task = taskService.getTaskById(id);
		var notes = noteService.getNotesForTasks(id);
		
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
 
		var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
		taskResponse.setNotes(notes);
		return ResponseEntity.ok(taskResponse);
	}

	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
		var task = taskService.addTasks(body.getTitle(), body.getDescription(), body.getDeadline());

		return ResponseEntity.ok(task);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException{
		var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
		
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(task);
		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
		if (e instanceof ParseException) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date format"));
		}
		e.printStackTrace();

		return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));

	}

}
