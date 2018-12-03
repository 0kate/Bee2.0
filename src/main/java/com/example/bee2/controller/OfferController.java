package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.service.PostService;

@Controller
public class OfferController {
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/bee/offer", method=RequestMethod.GET)
	public String offer(@RequestParam("user") String user, @RequestParam("postId") Long postId) {
		postService.offer(user, postId);
		
		return "redirect:/bee/post?id=" + postId.toString();
	}
}
