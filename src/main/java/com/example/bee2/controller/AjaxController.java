package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bee2.entity.ajax.GetFriendsCountEntity;
import com.example.bee2.entity.ajax.UserExistsResultEntity;
import com.example.bee2.service.UserService;

@Controller
public class AjaxController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/ajax/userExists", method=RequestMethod.GET)
	@ResponseBody
	public UserExistsResultEntity userExists(@RequestParam("username") String username) {
		UserExistsResultEntity result = new UserExistsResultEntity();
		result.setResult(userService.userExists(username));
		return result;
	}
	
	@RequestMapping(value="/bee/ajax/getFollower", method=RequestMethod.GET)
	@ResponseBody
	public GetFriendsCountEntity getFollower(@RequestParam("username") String username) {
		GetFriendsCountEntity result = new GetFriendsCountEntity();
		result.setResult(userService.getFollowerCount(username));
		return result;
	}
	
	@RequestMapping(value="/bee/ajax/getFollowing", method=RequestMethod.GET)
	@ResponseBody
	public GetFriendsCountEntity getFollowing(@RequestParam("username") String username) {
		GetFriendsCountEntity result = new GetFriendsCountEntity();
		result.setResult(userService.getFollowingCount(username));
		return result;
	}
}
