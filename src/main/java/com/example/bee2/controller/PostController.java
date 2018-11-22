package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.form.PostForm;
import com.example.bee2.service.PostService;
import com.example.bee2.service.UserService;

@Controller
public class PostController {
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/bee/post", method=RequestMethod.POST)
	public String post(@ModelAttribute PostForm postForm, Principal principal) {
		postService.addNewPost(postForm.getTitle(), postForm.getText(), userService.pickupUser(principal).getName(), createLink(postForm.getUrl()));
		return "redirect:/bee/top";
	}
	
	private String createLink(String url) {
		return "<a href\"=" + url + "\"></a>";
	}
}
