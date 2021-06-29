package com.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails,Integer>{

	public UserDetails findByName(String name);
	
	public UserDetails findByEmailAndPassword(String email , String password);
}
