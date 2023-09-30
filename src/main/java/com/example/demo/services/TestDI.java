package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;

@Service
public class TestDI {
	
//	@Autowired
//	private UserRoleRepository x;
	
	public TestDI() {
		//System.out.println("am injected dependency: " + x.toString());
	}


}
