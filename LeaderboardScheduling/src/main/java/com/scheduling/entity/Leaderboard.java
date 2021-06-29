package com.scheduling.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leaderboard {

	@Id
	private Integer leader_id;
	private String name;
	private Integer totalmarksobtained;
}
