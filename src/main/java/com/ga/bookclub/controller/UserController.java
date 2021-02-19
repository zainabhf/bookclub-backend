package com.ga.bookclub.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.bookclub.dao.UserDao;
import com.ga.bookclub.model.User;

@RestController
public class UserController {
 
	@Autowired
	private UserDao userDao;

	
	@GetMapping("/")
	public String show(){
		return "It's working !";
	}

	@PostMapping("/user/registration")
	 public HashMap<String, String> registration(@RequestBody User user) {
		
		 HashMap<String, String> response = new HashMap();
		 var it = userDao.findAll();
		
		 for(User dbUser : it) {
			 if(dbUser.getEmailAddress().equals(user.getEmailAddress())) {
				
				 response.put("message", "User Already Exists !");
				 return response;
			 }
		 }
		
		
		 // Password Encryption
//		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		 String newPassword = bCrypt.encode(user.getPassword());
		 user.setPassword(user.getPassword());
		 userDao.save(user);
		 response.put("message", "User Registered Successfully !");
		 return response;
	 }
//	@PostMapping("/user/login")
//	 public HashMap<String, String> login(@RequestBody User user){
//      ModelAndView mv = new ModelAndView();
//	
//		if(isUserLoggedIn()) {
//			mv.setViewName("user/profile");
//			
//			User user = userDao.findById(id);
//			mv.addObject("user", user);
//			/* mv.addObject("message", "Logged in successfully"); */
//			
//		}else {
//			mv.setViewName("user/login");
//			/* mv.addObject("message", "Email or password is incorrect"); */
//		}
//		HomeController hc = new HomeController();
//		hc.setAppName(mv, env);
//		return mv;
//	}
//		public boolean isUserLoggedIn() {
//			 
//			  HttpSession session = request.getSession();
//			 
//			  if(session.getAttribute("user") == null)
//				  return false;
//			 
//			  return true;
//			}
//	
	
	
	// HTTP POST REQUEST - 
	@GetMapping("/user/profile")
	public User profile (@RequestParam int id) {
	var user = userDao.findById(id);
	System.out.println(user);
	System.out.println(id);
	return user;
	}
	
	// HTTP Put REQUEST - user Edit
		@PutMapping("/user/edit")
		public User editProfile(@RequestBody User user) {
		  var newUser = userDao.save(user);
			return newUser;
		}
	
}
