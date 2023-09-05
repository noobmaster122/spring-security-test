package com.example.demo.controllers;

import com.example.demo.entities.*;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
public class LoginController {

	
	@PostMapping("/api/public/login")
	public ResponseEntity<String> authenticateUser(@RequestBody User user, HttpServletRequest request) {

		try {
			request.login(user.getUserName(), user.getPassword());
			System.out.println("je suis la " );
			System.out.println("user name : " + user.getUserName() + " user password: " + user.getPassword());
		    
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if( authentication.isAuthenticated() ) {
		        Object principal = authentication.getPrincipal();
		        if (principal instanceof User) {
		        User x = (User) principal;
				System.out.println("connected user: " +  x.getUsername());
		        }

			}
		} catch (ServletException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
	}

	@GetMapping("/api/public/logout")
	public ResponseEntity<String> logout(Authentication authentication) {
		if (authentication != null) {
			SecurityContextHolder.clearContext(); // Clear the security context
		}
		return ResponseEntity.ok("User logged out successfully.");

	}
	@PostMapping("/api/public/register")
	public ResponseEntity<String> register(@RequestBody User user, Authentication authentication) {
		System.out.println(user.toString());
		if (authentication != null) {
			// return server error
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Cannot register when a user is already logged in!");
	        
		}
		return ResponseEntity.ok("User registered successfully.");

	}

}