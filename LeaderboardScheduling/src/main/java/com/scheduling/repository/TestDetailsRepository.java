package com.scheduling.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.scheduling.entity.TestDetails;

public interface TestDetailsRepository extends JpaRepository<TestDetails, String>{

	@Query(value = "select * from test_details order by section_a_score+section_b_score+section_c_score desc limit 10" , nativeQuery = true )
	public List<TestDetails> toplist();
}
