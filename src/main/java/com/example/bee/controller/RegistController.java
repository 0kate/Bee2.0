package com.example.bee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee.form.RegistForm;
import com.example.bee.service.UserService;

@Controller
public class RegistController {
	@Autowired
	private UserService userInfoService;
	
	@RequestMapping(value="/bee/regist", method=RequestMethod.GET)
	public String registPage(Model model) {
		model.addAttribute(new RegistForm());
		return "regist";
	}
	
	@RequestMapping(value="/bee/regist", method=RequestMethod.POST)
	public String regist(@ModelAttribute RegistForm registForm) {
		userInfoService.registerUser(registForm.getUsername(), 
									 registForm.getAge(), 
									 registForm.getEmail(), 
									 registForm.getPassword(), 
									 registForm.getLocation());
		
		return "redirect:/bee/login";
	}
}
