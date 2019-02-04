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
public class FollowController {
  @Autowired
  private UserService userService;
  
  @Autowired
  private UserUtility userUtility;
  
  @RequestMapping(value="/bee/follow", method=RequestMethod.GET)
  public String follow(@RequestParam("fromuser") String fromUserName, 
                         @RequestParam("touser") String toUserName, 
                         @RequestParam(name="release", required=false) Boolean release) {
    if (release == null) {
      userService.follow(fromUserName, toUserName);
    } else {
      userService.release(fromUserName, toUserName);
    }
    
    return "redirect:/bee/profile?username=" + toUserName;
  }
  
  @RequestMapping(value="/bee/follow/get", method=RequestMethod.GET)
  public String getList(@RequestParam("type") String type, @RequestParam("name") String username, Model model, Principal principal) {
  	User user = userUtility.pickupUser(principal);
  	
  	model.addAttribute("user", user);
  	model.addAttribute("isAdmin", user.isAdmin());

  	switch (type) {
  		case "follower":
  			model.addAttribute("userList", userService.getFollowers(username));
  			break;
  		case "following":
  			model.addAttribute("userList", userService.getFollowings(username));
  			break;
  	}
  	
  	return "userlist";
  }
}
