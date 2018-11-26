package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.entity.User;
import com.example.bee2.service.UserService;

@Controller
public class ProfileController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/profile", method=RequestMethod.GET)
	public String profilePage(@RequestParam("username") String username, Model model, Principal principal) {
	    User user = userService.pickupUser(principal);
	    User targetUser = userService.findByName(username);
	    
	    model.addAttribute("user", user);
		model.addAttribute("targetUser", targetUser);
		model.addAttribute("following", userService.isFollowing(user, targetUser));
		
		return "profile";
	}
}
