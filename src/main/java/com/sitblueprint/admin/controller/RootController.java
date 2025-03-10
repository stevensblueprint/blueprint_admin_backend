package com.sitblueprint.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

	@GetMapping("/hello_world")
	public String root() {
		return "Welcome to Blueprint Admin API";
	}
}
