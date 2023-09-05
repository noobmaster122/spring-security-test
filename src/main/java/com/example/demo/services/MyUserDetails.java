package com.example.demo.services;

import java.util.Arrays;

import java.util.List;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.User;
import com.example.demo.entities.UserRoles;
import com.example.demo.entities.Role;

public class MyUserDetails implements UserDetails{
	
	private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
	
	public MyUserDetails(String name) {
		this.username = name;
	}

	public MyUserDetails(User user) {
		this.username = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = user.getUserRoles().stream()
                .map(UserRoles::getRole)
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
	}
	
	public MyUserDetails() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
