package com.jwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JwtauthenticationApplication {

	/*
	 * @Autowired private UserService us;
	 * 
	 * @Autowired private JwtUtil ju;
	 * 
	 * @PostMapping("/adduser") public String insert(@RequestBody User user) {
	 * us.adduser(user); return "Successfully added"; }
	 * 
	 * @PostMapping("/login") public String login(@RequestBody UserLogin ul) {
	 * String subject = ul.getEmail(); String token = ju.tokenGeneration(subject);
	 * return token; }
	 * 
	 * @GetMapping("/welcome") public String welcomeuser() { return "Welcome User";
	 * }
	 */

	@Bean
	public BCryptPasswordEncoder pwdencoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtauthenticationApplication.class, args);
	}

}
