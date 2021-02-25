package com.ga.bookclub.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ga.bookclub.config.JwtUtil;
import com.ga.bookclub.dao.UserDao;
import com.ga.bookclub.model.JwtResponse;
import com.ga.bookclub.model.User;

@RestController
public class UserController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	HttpServletRequest request;
	

	
	// To post the registration form
	 @PostMapping("/user/registration")
	 public HashMap<String, String> registration(@RequestBody User user) {

		 
		 
		 HashMap<String, String> response = new HashMap<String, String>();
		 
		 // Check to user is already registered or not
		 
		 var it = dao.findAll();
		 
		 for(User dbUser : it) {
			 if(dbUser.getEmailAddress().equals(user.getEmailAddress())) {

				 response.put("message", "User already exists");
				 return response;
				 
			 }
		 }
		 
		 
		 // Password Encryption
		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		 String newPassword = bCrypt.encode(user.getPassword());
		 user.setPassword(newPassword);
		 
		 dao.save(user);

		 
		 response.put("message", "User registered successfully");
		 return response;
		 
	 }
	 
	 @Autowired
	 AuthenticationManager authenticationManager;
	 
	 @Autowired
	 JwtUtil jwtUtil;
	 
	 @Autowired
	 UserDetailsService userDetailsService;
	 
	 @PostMapping("/user/authenticate")
	 public ResponseEntity<?> authenticate(@RequestBody User user) {
		 
		 try {
			 authenticationManager.authenticate(
					 new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword())
					 );
		 }
		 catch(BadCredentialsException e) {
			 String res = "Incorrect username or password";
			 return ResponseEntity.ok(res);
		 }
		 
		// Conitnue
		 
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmailAddress());
		
		String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println(jwtToken);
		return ResponseEntity.ok(new JwtResponse(jwtToken));
		 
	 }

}
