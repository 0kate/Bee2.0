package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.entity.User;
import com.example.bee2.form.PostForm;
import com.example.bee2.service.PostService;
import com.example.bee2.utility.UserUtility;

@Controller
public class PostController {
	@Autowired
	private UserUtility userUtility;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/bee/post", method=RequestMethod.GET)
	public String postDetails(@RequestParam("postId") Long id, Model model) {
		model.addAttribute("post", postService.findById(id));
		
		return "postdetails";
	}
	
	@RequestMapping(value="/bee/post", method=RequestMethod.POST)
	public String post(@ModelAttribute PostForm postForm, Principal principal) {
		postService.addNewPost(postForm.getTitle(), postForm.getText(), userUtility.pickupUser(principal).getName(), postForm.getUrl());
		return "redirect:/bee/top";
	}
	
	@RequestMapping(value="/bee/post/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("postId") Long id, Principal principal) {
		User user = userUtility.pickupUser(principal);
		postService.deletePost(id);
		return "redirect:/bee/profile?username=" + user.getName();
	}
}
