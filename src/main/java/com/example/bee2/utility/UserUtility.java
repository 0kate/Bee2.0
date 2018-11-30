package com.example.bee2.utility;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.bee2.entity.User;
import com.example.bee2.entity.UserInfo;

@Component
public class UserUtility {
	public Boolean isFollowing(User user1, User user2) {
	  if (user1.noFollowing()) return false;
	  return user1.getFollowing().contains(user2);
	}
	
	public User pickupUser(Principal principal) {
		Authentication auth = (Authentication)principal;
		UserInfo user = (UserInfo) auth.getPrincipal();
		
		return user.getUser();
	}
}
