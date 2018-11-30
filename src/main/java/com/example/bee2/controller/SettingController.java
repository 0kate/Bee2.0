package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.entity.User;
import com.example.bee2.service.UserService;
import com.example.bee2.utility.UserUtility;

@Controller
public class SettingController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserUtility userUtility;
	
	@RequestMapping(value="/bee/setting", method=RequestMethod.GET)
	public String settingPage(Model model, Principal principal) {
		User user = userUtility.pickupUser(principal);
		model.addAttribute("user", user);
		
		return "setting";
	}
	
	@RequestMapping(value="/bee/unsubscribe/request", method=RequestMethod.GET)
	public String unsubscribeRequest(Model model, Principal principal) {
		model.addAttribute("user", userUtility.pickupUser(principal));
		return "confirm.html";
	}
	
	@RequestMapping(value="/bee/unsubscribe/confirm", method=RequestMethod.GET)
	public String unsubscribeConfirm(@RequestParam("select") String select, Principal principal) {
		String nextPath = null;
		switch (select) {
			case "yes":
				userService.delete(userUtility.pickupUser(principal));
				nextPath = "redirect:/bee/login";
				break;
			case "no":
				nextPath = "redirect:/bee/top";
				break;
			default: break;
		}
		
		return nextPath;
	}
}
