package com.project.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.Dao.UserRepository;
import com.project.entity.User;

@RestController
public class Usercontroller {

	private UserRepository repo;
	
	public Usercontroller(UserRepository repo) {
		this.repo = repo;
	}
	
	
	@PostMapping("/adduser")
	public String adduser(@RequestBody User user) {
		repo.save(user);
		return "Successfully added";
	}
	
	
	
	@GetMapping("/allusers")
	public Iterable<User> getallusers (){
		return  repo.findAll();
	}
	
	
	
	@PutMapping("/update")
	@CachePut(value = "users" , key="#id")
	public User update(@RequestParam String id , @RequestBody User user) {
		repo.save(user);
		return repo.findById(id).get();
	}
	
	
	
	@GetMapping("/get/{id}")
	@Cacheable(value ="users" , key="#id")
	public User getbyid(@PathVariable String id) {
		System.out.println("From database by id");
		 Optional<User> user = repo.findById(id);
		return user.get();
	}
	
	
	
	@GetMapping("/get")
	public List<User> getbyname(@RequestParam String name){
		return repo.findByName(name);
	}
	
	
	
	@GetMapping("/get/{city}")
	public List<User> getbycity(@PathVariable String city){
		return repo.findByCity(city);
	}
	
	
	@GetMapping("/get/{name}/{city}")
	public List<User> bynameandcity(@PathVariable String name , @PathVariable String city){
		return repo.findByNameAndCity(name, city);
	}
	
	
	@DeleteMapping("/delete/{id}")
	@CacheEvict(value = "users" , key = "#id")
	public String removebyid(@PathVariable String id) {
		repo.deleteById(id);
		return "Removed successfully";
	}
	
	
	@DeleteMapping("/deleteall")
	@CacheEvict(value = "users" , allEntries = true)
	public String removeall() {
		repo.deleteAll();
		return "All removed";
	}
	
}
