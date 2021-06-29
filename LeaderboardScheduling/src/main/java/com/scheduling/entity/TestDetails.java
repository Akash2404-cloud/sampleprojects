package com.scheduling.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TestDetails {
	
	@Id
	@GeneratedValue
	private Integer stud_id;
	private String name;
	private Integer section_A_score;
	private Integer section_B_score;
	private Integer section_C_score;

	public TestDetails() {
		super();
	}

	public TestDetails(String name, Integer section_A_score, Integer section_B_score,
			Integer section_C_score) {
		super();
		this.name = name;
		this.section_A_score = section_A_score;
		this.section_B_score = section_B_score;
		this.section_C_score = section_C_score;
	}
	
}
