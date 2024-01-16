package com.example.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;

@Service
@DependsOn("userRepository")
public class CustomUserDetailsService implements UserDetailsService{
	
	//@Autowired
	private UserRepository userRepo;
	
	
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("am user name: " + userRepo);
		
		Optional<User> x = userRepo.findByUserName(username);
        x.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        //System.out.println("am here bitches" + username);
		return x.get();
	}

}
