package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.service.PostService;
import com.example.bee2.service.UserService;

@Controller
public class OfferController {
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postSerivce;
	
	@RequestMapping(value="/bee/offer", method=RequestMethod.GET)
	public String offer(@RequestParam("user") String user, @RequestParam("post") String post) {
		postService.offer(user, post);
	}
}
