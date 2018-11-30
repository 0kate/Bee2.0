package com.example.bee2.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.service.UserService;

@Controller
public class AutoGenerateController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/autogenerate/regist", method=RequestMethod.GET)
	public String autogenerateUser(@RequestParam(name="userCount", required=false) String count) {
		String nextPath = null;
		
		if (StringUtils.isEmpty(count)) {
			nextPath = "autogenerate";
		} else {
			String userName = "User" + count;
			userService.registerUser(userName, 20L, userName + "@bee.com", "password", "Japan");
			
			nextPath = "redirect:/bee/autogenerate/regist?userCount=" + Integer.toString(Integer.parseInt(count) + 1);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return nextPath;
	}
	
	@RequestMapping(value="/bee/autogenerate/follow", method=RequestMethod.GET)
	public String autogenerateFollow(@RequestParam(name="fromUser", required=false) String fromUser, @RequestParam(name="toUser", required=false) String toUser) {
		Random randGenerator = new Random();
		
		if (!StringUtils.isEmpty(fromUser) || !StringUtils.isEmpty(toUser)) {
			 userService.follow(fromUser, toUser);
		}
		
		String nextFromUser = "User" + (randGenerator.nextInt(100) + 1);
		String nextToUser = "User" + (randGenerator.nextInt(100) + 1);
		
		return "redirect:/bee/autogenerate/follow?fromsuer=" + nextFromUser + "&touser=" + nextToUser;
	}
}
