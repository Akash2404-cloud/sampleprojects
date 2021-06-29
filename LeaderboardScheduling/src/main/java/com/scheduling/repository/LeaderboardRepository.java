package com.scheduling.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.scheduling.entity.Leaderboard;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, String> {
	
	@Query(nativeQuery = true , value = "select * from leaderboard order by totalmarksobtained desc")
	public List<Leaderboard> sortleaderboard();

}
