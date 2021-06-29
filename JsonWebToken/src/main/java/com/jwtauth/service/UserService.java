package com.jwtauth.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtauth.entity.User;
import com.jwtauth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository ur;
	
	public String adduser(User user) {
		ur.save(user);
		return "User added successfully";
	}

	public Optional<User> byEmail(String email ){
		return Optional.ofNullable(ur.findByEmail(email));
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = byEmail(email);
		String mail = user.get().getEmail();
		String pwd = user.get().getPassword();
		return new org.springframework.security.core.userdetails.User(mail, pwd, new ArrayList<>());
	}

}
