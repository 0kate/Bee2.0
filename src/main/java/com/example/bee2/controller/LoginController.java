package com.example.bee2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.utility.RequestUtility;

@Controller
public class LoginController {
  @Autowired
  private RequestUtility requestUtility;
  
  @RequestMapping(value="/bee/login", method=RequestMethod.GET)
  public String loginPage(@RequestHeader(value="User-Agent", required=false) String userAgent) {
    return requestUtility.isMobile(userAgent) ? "login-mobile" : "login";
  }
	
  @RequestMapping(value="/error", method=RequestMethod.GET)
  public String loginError() {
    return "login";
  }
}
