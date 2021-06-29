package com.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeaderboardSchedulingApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(LeaderboardSchedulingApplication.class, args);
	}

}
