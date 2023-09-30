package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
}
