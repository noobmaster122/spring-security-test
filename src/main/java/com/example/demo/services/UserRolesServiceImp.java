package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Role;
import com.example.demo.interfaces.UserRolesService;
import com.example.demo.repositories.RoleRepository;

@Service
public class UserRolesServiceImp implements UserRolesService {
	  @Autowired
	    private RoleRepository roleRepository; // Inject your Role repository

	    @Override
	    public Role getDefaultRole() {
	        return roleRepository.findByName("ROLE_ADMIN"); // Replace with your logic to fetch the default role
	    }
}
