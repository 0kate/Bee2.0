package com.example.bee2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class LoginController {
	@RequestMapping(value="/bee/login", method=RequestMethod.GET)
	public String loginPage(@RequestHeader(value="User-Agent", required=false) String userAgent) {
	  String[] mobileAgents = { "iPhone", "iPad", "iPod", "iPod touch", "Android" };
	  boolean isMobile = false;
	  
	  for (String agent : mobileAgents) {
	    isMobile |= userAgent.contains(agent);
	  }
	  
	  return isMobile ? "login-mobile" : "login";
	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String loginError() {
	  return "login";
	}
}
