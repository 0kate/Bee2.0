package com.example.bee2.service;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
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
		registUser(username, age, email, password, location, "ROLE_USER");
	}
	
	public void registerAdmin(String username, Long age, String email, String password, String location) {
		registUser(username, age, email, password, location, "ROLE_ADMIN");
	}
	
	public void registUser(String username, Long age, String email, String password, String location, String role) {
		userRepository.save(new User(username, age, email, passwordEncoder.encode(password), location, role));
	}
	
	public void addNewUser(User user) {
		userRepository.save(user);
	}
	
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
	
	public Collection<User> findByNameLike(String name) {
		return userRepository.findByNameContaining(name);
	}
	
	public void follow(String fromUserName, String toUserName) {
	  User from = userRepository.findByName(fromUserName);
	  User to = userRepository.findByName(toUserName);
	  
	  if (from.noFollowing()) {
	    from.createFollowingSet();
	  }
	  
	  from.getFollowing().add(to);
	  userRepository.save(from);
	  
	  updatePrincipal(from);
	}
	
	public void release(String fromUserName, String toUserName) {
	  User from = userRepository.findByName(fromUserName);
	  User to = userRepository.findByName(toUserName);
	  
	  from.getFollowing().remove(to);
	  userRepository.save(from);
	  
	  updatePrincipal(from);
	}
	
	public Boolean isFollowing(User user1, User user2) {
	  if (user1.noFollowing()) return false;
	  return user1.getFollowing().contains(user2);
	}
	
	public User pickupUser(Principal principal) {
		Authentication auth = (Authentication)principal;
		UserInfo user = (UserInfo) auth.getPrincipal();
		
		return user.getUser();
	}
	
	public void updatePrincipal(User user) {
	  UserInfo userInfo = new UserInfo(user, getAuthorities(user));
	  Authentication auth = new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), userInfo.getAuthorities());
	  SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public void loginFailed(String username) {
		User user = userRepository.findByName(username);
		user.setFailed(user.getFailed() + 1L);
		if (user.getFailed() >= 3) {
			user.setLockout(true);
		}
		userRepository.save(user);
	}
}
