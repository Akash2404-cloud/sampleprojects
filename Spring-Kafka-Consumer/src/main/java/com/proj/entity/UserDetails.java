package com.proj.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserDetails {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String phone;
	
}
