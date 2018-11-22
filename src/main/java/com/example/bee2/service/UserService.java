package com.example.bee2.service;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.bee2.entity.User;
import com.example.bee2.entity.UserInfo;
import com.example.bee2.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) throw new UsernameNotFoundException("Username is empty");
		
		Optional<User> userOptional = Optional.ofNullable(userRepository.findByName(username));
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found : " + username));
		
		return new UserInfo(user, getAuthorities(user));
	}

	public Collection<GrantedAuthority> getAuthorities(User user) {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
	
	public void registerUser(String username, Long age, String email, String password, String location) {
		userRepository.save(new User(username, age, email, passwordEncoder.encode(password), location));
	}
	
	public void addNewUser(User user) {
		userRepository.save(user);
	}
	
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
	
	public Collection<User> findByNameLike(String name) {
		return userRepository.findByNameLike(name);
	}
	
	public User pickupUser(Principal principal) {
		Authentication auth = (Authentication)principal;
		UserInfo user = (UserInfo) auth.getPrincipal();
		
		return user.getUser();
	}
}
