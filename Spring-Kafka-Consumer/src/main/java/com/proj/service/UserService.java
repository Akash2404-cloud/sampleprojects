package com.proj.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.proj.entity.User;
import com.proj.entity.UserDetails;
import com.proj.repository.UserRepository;

@Service
public class UserService {

	private UserRepository ur;
	
	public UserService(UserRepository ur) {
		this.ur=ur;
	}
	
	@KafkaListener(groupId = "userconsumer" , topics = "topic01" , containerFactory = "kafkalistener")
	public void adduser(User user) {
		UserDetails newuser = new UserDetails();
		newuser.setName(user.getName());
		newuser.setEmail(user.getEmail());
		newuser.setPassword(user.getPassword());
		newuser.setPhone(user.getPhone());
		ur.save(newuser);
	}
}
