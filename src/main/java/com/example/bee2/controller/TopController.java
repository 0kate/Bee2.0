package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.entity.UserInfo;
import com.example.bee2.form.PostForm;
import com.example.bee2.service.MessageService;
import com.example.bee2.service.PostService;

@Controller
public class TopController {
	@Autowired
	private PostService postService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/bee/top", method=RequestMethod.GET)
	public String topPage(Model model, Principal principal) {
		Authentication auth = (Authentication)principal;
		UserInfo user = (UserInfo) auth.getPrincipal();
		
		model.addAttribute("user", user.getUser());
		model.addAttribute("postList", postService.getAllPost());
		model.addAttribute("postForm", new PostForm());
		model.addAttribute("messageList", messageService.findByReciever(user.getUsername()));
		
		return "top";
	}
}
