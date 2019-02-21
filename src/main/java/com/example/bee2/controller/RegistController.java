package com.example.bee2.controller;

import java.util.HashMap;
import java.util.Map;

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
	public String regist(@ModelAttribute RegistForm registForm, Model model) {
		Map<String, String> userInformations = new HashMap<>();
		
		String username = registForm.getUsername();
		userInformations.put("username", username);
		userInformations.put("age", registForm.getAge().toString());
		userInformations.put("email", registForm.getEmail());
		userInformations.put("password", registForm.getPassword());
		userInformations.put("location", registForm.getLocation());
		
		if (userService.userExists(username)) {
			model.addAttribute(new RegistForm());
			model.addAttribute("userExistsMessage", "this name is used.");
			return "regist";
		}
		
		if (username.startsWith("beeAdministrator-")) {
			userInformations.put("username", username.replaceFirst("beeAdministrator-", ""));
			userService.registerAdmin(userInformations);
		} else {
			userService.registerUser(userInformations);
		}
		
		return "redirect:/bee/login";
	}
}
