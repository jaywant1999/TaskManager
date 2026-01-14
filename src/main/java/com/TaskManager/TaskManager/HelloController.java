package com.TaskManager.TaskManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	String hello() {
		return "Hello I Am Task Manager";
	}
}
