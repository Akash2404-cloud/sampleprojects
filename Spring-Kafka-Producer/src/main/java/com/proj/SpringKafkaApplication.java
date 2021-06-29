package com.proj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.entity.User;

@SpringBootApplication
@RestController
public class SpringKafkaApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> kt;
	
	
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user) {
		kt.send("topic01", user);
		return "User has been successfully added";
	}
	

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

}
