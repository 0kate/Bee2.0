package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.entity.User;
import com.example.bee2.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/admin", method=RequestMethod.GET)
	public String adminPage() {
		return "admin";
	}
	
	@RequestMapping(value="/bee/admin/userlist", method=RequestMethod.GET)
	public String adminUserList(Model model, Principal principal) {
		User user = userService.pickupUser(principal);
		model.addAttribute("user", user);
		model.addAttribute("isAdmin", user.isAdmin());
		model.addAttribute("userList", userService.findAll());
		return "userlist";
	}
}
