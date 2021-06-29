package com.jwtauth.config;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.jwtauth.entity.User;
import com.jwtauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Autowired
	private UserRepository repo;
	
	public JwtUtil(UserRepository repo) {
		this.repo=repo;
	}
	
	@Value("${secret.key}")
	private String secret;
	
	public String getUser(String token) {
		return getClaims(token).getSubject();
	}
	
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	
	public Claims getClaims(String token) {
		return Jwts.parser()
				   .setSigningKey(secret)
				   .parseClaimsJws(token)
				   .getBody();
	}
	
	public String tokenGeneration(String user) {
		
		return Jwts.builder()
				   .setSubject(user)
				   .setIssuedAt(new Date (System.currentTimeMillis()))
				   .setExpiration(new Date (System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				   .signWith(SignatureAlgorithm.HS256, secret)
				   .compact();
	}
	
	 public Boolean validateToken(String token, UserDetails userDetails) {
	        final String id = getUser(token);
	        Optional<User> user = repo.findById(Integer.parseInt(id));
	        return (user.get().getEmail().equals(userDetails.getUsername()) && user.get().getPassword().equals(userDetails.getPassword()));
	  }
}
