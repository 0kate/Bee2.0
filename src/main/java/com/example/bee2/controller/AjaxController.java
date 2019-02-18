package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bee2.service.UserService;

@Controller
public class AjaxController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/userExists", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean userExists(@RequestParam("username") String username) {
		return userService.userExists(username);
	}
}
