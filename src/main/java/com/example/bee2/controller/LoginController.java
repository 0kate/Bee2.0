package com.example.bee2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class LoginController {
	@RequestMapping(value="/bee/login", method=RequestMethod.GET)
	public String loginPage(@RequestParam(name="error", required=false) Boolean error, Model model) {
		if (error != null) model.addAttribute("error", true);
		return "login";
	}
}
