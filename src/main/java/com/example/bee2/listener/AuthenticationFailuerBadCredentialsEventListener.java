package com.example.bee2.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.example.bee2.service.UserService;

@Component
public class AuthenticationFailuerBadCredentialsEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		String username = event.getAuthentication().getName();
		userService.loginFailed(username);
	}
}
