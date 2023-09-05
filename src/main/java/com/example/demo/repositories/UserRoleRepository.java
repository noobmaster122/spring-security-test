package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.UserRoles;

public interface UserRoleRepository extends JpaRepository<UserRoles, Integer>{

}
