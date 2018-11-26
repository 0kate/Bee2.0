package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.form.RegistForm;
import com.example.bee2.service.UserService;

@Controller
public class RegistController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/regist", method=RequestMethod.GET)
	public String registPage(Model model) {
		model.addAttribute(new RegistForm());
		return "regist";
	}
	
	@RequestMapping(value="/bee/regist", method=RequestMethod.POST)
	public String regist(@ModelAttribute RegistForm registForm) {
		String username = registForm.getUsername();
		Long age = registForm.getAge();
		String email = registForm.getEmail();
		String password = registForm.getPassword();
		String location = registForm.getLocation();
		
		if (username.startsWith("admin")) {
			username = username.replace("admin", "");
			userService.registerAdmin(username, age, email, password, location);
		} else {
			userService.registerUser(username, age, email, password, location);
		}
		
		return "redirect:/bee/login";
	}
}
