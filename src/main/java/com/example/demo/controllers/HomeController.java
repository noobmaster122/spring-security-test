package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	@PostMapping("/testing")
	public String tsting() {
		return "testing";
	}
	
}
