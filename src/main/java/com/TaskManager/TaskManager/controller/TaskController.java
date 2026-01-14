package com.TaskManager.TaskManager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManager.TaskManager.dto.CreateTaskDTO;
import com.TaskManager.TaskManager.entities.TaskEntity;
import com.TaskManager.TaskManager.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<TaskEntity>> getTasks(){
		var task = taskService.getTasks();
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(task);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
		var task = taskService.getTaskById(id);
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(task);
	}
	
	
	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body){
		var task = taskService.addTasks(body.getTitle(), body.getDescription(), body.getDeadline());
		
		return ResponseEntity.ok(task);
	}

}
