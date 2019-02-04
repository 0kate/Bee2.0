package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.service.UserService;

@Controller
public class LockoutController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/lockout", method=RequestMethod.GET)
	public String lockout(@RequestParam("type") String type, @RequestParam("username") String username) {
		switch (type) {
			case "unlock":
				userService.unlock(username);
				break;
			case "lock":
				userService.lock(username);
				break;
		}
		
		return "redirect:/bee/admin/userlist";
	}
}
