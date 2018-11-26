package com.example.bee2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.entity.User;
import com.example.bee2.form.MessageForm;
import com.example.bee2.service.MessageService;
import com.example.bee2.service.UserService;

@Controller
public class MessageController {
  @Autowired
  private UserService userService;
  @Autowired
  private MessageService messageService;
  
  @RequestMapping(value="/bee/message", method=RequestMethod.GET)
  public String messagePage(Model model, Principal principal) {
    User user = userService.pickupUser(principal);
    model.addAttribute("user", user);
    model.addAttribute("isAdmin", user.isAdmin());
    model.addAttribute("messageForm", new MessageForm());
    return "message";
  }
  
  @RequestMapping(value="/bee/message", method=RequestMethod.POST)
  public String sendMessage(@ModelAttribute MessageForm messageForm, Principal principal) {
    String sender = userService.pickupUser(principal).getName();
    String reciever = messageForm.getReciever();
    String title = messageForm.getTitle();
    String text = messageForm.getText();
    
    messageService.sendMessage(sender, reciever, title, text);
    
    return "redirect:/bee/message";
  }
}
