package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bee2.service.UserService;

@Controller
public class FollowController {
  @Autowired
  private UserService userService;
  
  @RequestMapping(value="/bee/follow", method=RequestMethod.GET)
  public String follow(@RequestParam("fromuser") String fromUserName, @RequestParam("touser") String toUserName) {
    userService.follow(fromUserName, toUserName);
    return "redirect:/bee/profile?username=" + toUserName; 
  }
}
