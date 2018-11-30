package com.example.bee2.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.form.RegistForm;
import com.example.bee2.service.UserService;

@Controller
public class AutoGenerateController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/bee/autogenerate", method=RequestMethod.GET)
	public String autogeneratePage(Model model) {
		model.addAttribute("registForm", new RegistForm());
		return "autoGenerate";
	}
	
	@RequestMapping(value="/bee/autogenerate", method=RequestMethod.POST)
	public String autogenerate(@CookieValue(name="userCount", required=false) String count, @ModelAttribute("registForm") RegistForm registForm, HttpServletResponse response) {
		userService.registerUser(registForm.getUsername(), registForm.getAge(), registForm.getEmail(), registForm.getPassword(), registForm.getLocation());
		
		Optional<String> countOptional = Optional.ofNullable(count);
		count = countOptional.map(cnt -> Integer.toString(Integer.parseInt(cnt) + 1)).orElseGet(() -> "0");
		
		response.addCookie(new Cookie("userCount", count));
		
		return "redirect:/bee/autogenerate";
	}
}
