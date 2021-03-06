package com.jwtauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtauth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmailAndPassword(String email,String pwd);
	
	public User findByEmail(String email);
}
