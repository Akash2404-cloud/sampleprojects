package com.scheduling.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.scheduling.entity.Leaderboard;
import com.scheduling.entity.TestDetails;
import com.scheduling.repository.LeaderboardRepository;
import com.scheduling.repository.TestDetailsRepository;

@RestController
public class SchedulingController {

	private TestDetailsRepository tr;
	
	private LeaderboardRepository lr;
	
	public SchedulingController(TestDetailsRepository tr , LeaderboardRepository lr ) {
		this.tr = tr;
		this.lr = lr;
	}
	
	@PostMapping("/add")
	public String addDetails(@RequestBody TestDetails td) {
		tr.save(td);
		return "Record saved";
	}
	
	@GetMapping("/leaderboard")
	public List<Leaderboard> getboard(){
		return lr.sortleaderboard();
	}
}
