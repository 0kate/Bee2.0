package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.entity.User;
import com.example.bee2.form.PostForm;
import com.example.bee2.service.MessageService;
import com.example.bee2.service.PostService;
import com.example.bee2.service.UserService;

@Controller
public class TopController {
    @Autowired
    private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/bee/top", method=RequestMethod.GET)
	public String topPage(Model model, Principal principal) {
	    User user = userService.pickupUser(principal);
		model.addAttribute("user", user);
		model.addAttribute("isAdmin", user.isAdmin());
		model.addAttribute("postList", postService.getAllPost());
		model.addAttribute("postForm", new PostForm());
		model.addAttribute("messageList", messageService.findByReciever(user.getName()));
		
		return "top";
	}
}
