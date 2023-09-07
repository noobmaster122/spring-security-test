package com.example.demo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.entities.UserRoles;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;

@Service
public class UserRegistrationService {

	@Autowired
	UserRepository userRepo;
	
	
	
	@Autowired
	UserRoleRepository userRoleRepo;
	
	@Transactional
	public void saveNewUser(User newUser) {
		userRepo.save(newUser);
	}
	
	@Transactional
	public void saveNewUserRole(UserRoles userRole) {
		userRoleRepo.save(userRole);
	}

}
