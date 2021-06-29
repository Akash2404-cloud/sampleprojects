package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ElasticandredisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticandredisApplication.class, args);
	}

}
