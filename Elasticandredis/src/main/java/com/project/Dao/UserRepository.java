package com.project.Dao;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.project.entity.User;

public interface UserRepository extends ElasticsearchRepository<User,String>{

	public List<User> findByName(String name);
	
	public List<User> findByCity(String city);
	
	public List<User> findByNameAndCity(String name , String city);
}
