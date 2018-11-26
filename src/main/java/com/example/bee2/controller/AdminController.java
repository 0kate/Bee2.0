package com.example.bee2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	@RequestMapping(value="/bee/admin", method=RequestMethod.GET)
	public String adminPage() {
		return "admin";
	}
}
