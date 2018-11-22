package com.example.bee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee.service.UserService;

@Controller
public class FindController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/find", method=RequestMethod.GET)
	public String findUser(@RequestParam String username, Model model) {
		model.addAttribute("userList", userService.findByNameLike(username));
		return "userlist";
	}
}
