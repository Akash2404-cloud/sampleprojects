package com.jwtauth.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.config.JwtUtil;
import com.jwtauth.entity.User;
import com.jwtauth.entity.UserLogin;
import com.jwtauth.repository.UserRepository;
import com.jwtauth.service.UserService;

@RestController
public class UserController {

	private UserService us;

	private JwtUtil ju;
	
	private UserRepository repo;
	
	public UserController (UserService us , JwtUtil ju , UserRepository repo) {
		this.us=us;
		this.ju = ju;
		this.repo = repo;
	}

	@PostMapping("/adduser")
	public String insert(@RequestBody User user) {
		us.adduser(user);
		return "Successfully added";
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLogin ul) {
		Optional<User> user = repo.findByEmailAndPassword(ul.getEmail(),ul.getPassword());
		if(user.isPresent()) {
	    String token = ju.tokenGeneration(user.get().getId().toString());
		return new ResponseEntity<String>(token,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No Such Member Exists" , HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/welcome")
	public String welcomeuser() {
		return "Welcome User";
	}
}
