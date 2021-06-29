package com.jwtauth.filter;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.jwtauth.config.JwtUtil;
import com.jwtauth.entity.User;
import com.jwtauth.repository.UserRepository;
import com.jwtauth.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter{

	private JwtUtil ju;
	
	private UserService us;
	
	private UserRepository repo;
	
	public JwtFilter(JwtUtil ju , UserService us , UserRepository repo ) {
		this.ju = ju;
		this.us=us;
		this.repo =repo;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
			
	String authorizationHeader = request.getHeader("Authorization");
	
	 String token = null;
     String userEmail = null;
     
     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
         token = authorizationHeader.substring(7);
         Optional<User> user = repo.findById(Integer.parseInt(ju.getUser(token)));
         userEmail = user.get().getEmail();
     }
     
     if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

         UserDetails userDetails = us.loadUserByUsername(userEmail);

         if (ju.validateToken(token, userDetails)) {

             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             usernamePasswordAuthenticationToken
                     .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         }
     }
     filterChain.doFilter(request, response);
	}

}
