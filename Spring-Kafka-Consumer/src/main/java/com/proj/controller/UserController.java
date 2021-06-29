package com.proj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.entity.UserDetails;
import com.proj.entity.UserLogin;
import com.proj.repository.UserRepository;

@RestController
public class UserController {

	private UserRepository ur;
	
	public UserController(UserRepository ur) {
		this.ur=ur;
	}
	
	@GetMapping("/find/{name}")
	public UserDetails byname(@PathVariable String name ) {
		return ur.findByName(name);
	}
	
	
	@PostMapping("/login")
	public UserDetails bynameandmail(@RequestBody UserLogin ul) {
		return ur.findByEmailAndPassword(ul.getEmail(), ul.getPassword());
	}
	
}
