package com.example.bee2.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.entity.Post;
import com.example.bee2.entity.User;
import com.example.bee2.service.PostService;
import com.example.bee2.service.UserService;

@Controller
public class AutoGenerateController {
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	
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
		
		return nextPath;
	}
	
	@RequestMapping(value="/bee/autogenerate/follow", method=RequestMethod.GET)
	public String autogenerateFollow(@RequestParam(name="fromuser", required=false) String fromUser, @RequestParam(name="touser", required=false) String toUser) {
		if (!StringUtils.isEmpty(fromUser) && !StringUtils.isEmpty(toUser)) userService.followAuto(fromUser, toUser);
		
		int userNum = userService.findAll().size();
		
		String nextFromUser = "User" + rangeRandom(1, userNum);
		String nextToUser = "User" + rangeRandom(1, userNum);
		
		return "redirect:/bee/autogenerate/follow?fromuser=" + nextFromUser + "&touser=" + nextToUser;
	}
	
	@RequestMapping(value="/bee/autogenerate/post", method=RequestMethod.GET)
	public String autogeneratePost(@RequestParam(name="postCount", required=false) String count) {
		int userNum = userService.findAll().size();
		int postNum = postService.findAll().size();
		
		if (StringUtils.isEmpty(count)) {
			count = Integer.toString(postNum + 1);
		}
		
		String postedUser = "User" + rangeRandom(1, userNum);
		
		postService.addNewPost("Post" + count, "Auto generated", postedUser, "www.github.com/" + postedUser);
		
		return "redirect:/bee/autogenerate/post?postCount=" + Integer.toString(Integer.parseInt(count) + 1);
	}
	
	@RequestMapping(value="/bee/autogenerate/offer", method=RequestMethod.GET)
	public String autogenerateOffer(@RequestParam(name="username", required=false) String username, @RequestParam(name="postId", required=false) Long postId) {
		if (!StringUtils.isEmpty(username) && postId != null) {
			Post post = postService.findById(postId);
			if (post != null) {
				postService.offer(username,  postId);
			}
		}
			
		String nextUsername = "User" + rangeRandom(1, userService.findAll().size());
		String nextPostId = Integer.toString(rangeRandom(1, Integer.parseInt(postService.searchMaxId().toString())));
		
		return "redirect:/bee/autogenerate/offer?username=" + nextUsername + "&postId=" + nextPostId; 
	}
	
	private int rangeRandom(int start, int end) {
		Random randGenerator = new Random();
		
		int rand;
		do {
			rand = randGenerator.nextInt(end) + 1;
		} while (rand < start);
		
		return rand;
	}
}
