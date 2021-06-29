package com.scheduling.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scheduling.entity.Leaderboard;
import com.scheduling.entity.TestDetails;
import com.scheduling.repository.LeaderboardRepository;
import com.scheduling.repository.TestDetailsRepository;

@Service
public class Leaderboardservice {

	private TestDetailsRepository tr;
	
	private LeaderboardRepository lr;
	
	
	public Leaderboardservice(TestDetailsRepository tr ,LeaderboardRepository lr ) {
		this.tr = tr;
		this.lr = lr;
	}
	
	@PostConstruct
	public void addingsampledata() {
		List<TestDetails> list = Arrays.asList(
				new TestDetails ("A",12,14,15),
				new TestDetails ("B",11,15,12),
				new TestDetails ("C",15,12,13),
				new TestDetails ("D",17,15,17),
				new TestDetails ("E",12,13,8),
				new TestDetails ("F",12,15,13),
				new TestDetails ("G",15,12,10),
				new TestDetails ("H",17,15,16),
				new TestDetails ("I",11,12,8),
				new TestDetails ("J",12,16,14),
				new TestDetails ("K",15,19,17),
				new TestDetails ("L",12,12,13),
				new TestDetails ("M",14,16,15),
				new TestDetails ("N",12,16,15),
				new TestDetails ("O",17,12,19),
				new TestDetails ("P",12,15,20)
				);
		tr.saveAll(list);
		List<TestDetails> list1 = tr.toplist();
		list1.forEach(ele -> {
			Integer total = ele.getSection_A_score()+ele.getSection_B_score()+ele.getSection_C_score();
			lr.save( new Leaderboard (ele.getStud_id() , ele.getName() , total));
		});
	}
		
//	@Scheduled(cron = "0/60 * * * * *")
	@Scheduled(cron = "0 0 * * * *")
	public void addtoleaderboard() {
		lr.deleteAll();
		List<TestDetails> list = tr.toplist();
		list.forEach(ele -> {
			Integer total = ele.getSection_A_score()+ele.getSection_B_score()+ele.getSection_C_score();
			lr.save( new Leaderboard (ele.getStud_id() , ele.getName() , total));
		});
	}
}
