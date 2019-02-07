package com.example.bee2.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bee2.entity.User;
import com.example.bee2.service.UserService;
import com.example.bee2.utility.UserUtility;

@Controller
public class ProfileController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserUtility userUtility;
	
	@RequestMapping(value="/bee/profile", method=RequestMethod.GET)
	public String profilePage(@RequestParam("username") String username, Model model, Principal principal) {
	  User user = userUtility.pickupUser(principal);
	  User targetUser = userService.findByName(username);
	    
	  model.addAttribute("user", user);
	  model.addAttribute("isAdmin", user.isAdmin());
		model.addAttribute("targetUser", targetUser);
		model.addAttribute("myself", user.equals(targetUser));
		model.addAttribute("isFollowing", userUtility.isFollowing(user, targetUser));
		model.addAttribute("follower", userService.getFollowerCount(user.getName()));
		model.addAttribute("following", userService.getFollowingCount(user.getName()));
		model.addAttribute("mypostList", userService.findByPosted(user.getName()));
		model.addAttribute("maybeFriends", userService.getMaybeFriends(user.getName()));
		
		return "profile";
	}
	
	@RequestMapping(value="/bee/profile/image/select", method=RequestMethod.GET)
	public String profileImageSelect(Model model, Principal principal) {
		User user = userUtility.pickupUser(principal);
		model.addAttribute(user);
		
		return "profile_image";
	}
	
	@RequestMapping(value="/bee/profile/image", method=RequestMethod.POST)
	public String profileImage(@RequestParam("upload_file") MultipartFile multipartFile, @RequestParam("file_type") String fileType, Model model, Principal principal) {
		User user = userUtility.pickupUser(principal);
		model.addAttribute("user", user);
		
		String dispatch_page = "redirect:/bee/profile?username=" + user.getName();
		String springImagePath = "/assets/profile_image/" + user.getName() + fileType;
		String filePath = "C:/Users/177010/Documents/GitHub/Bee2.0/src/main/resources/static/" + springImagePath;
		
		try {
			File uploadFile = new File(filePath);
			if (!uploadFile.exists()) uploadFile.createNewFile();
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			uploadFileStream.write(bytes);
			uploadFileStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			dispatch_page = "error";
		}
		
		userService.updateProfileImage(user, springImagePath);
		
		return dispatch_page;
	}
}
