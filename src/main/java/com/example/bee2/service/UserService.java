package com.example.bee2.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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

import com.example.bee2.entity.Post;
import com.example.bee2.entity.User;
import com.example.bee2.entity.UserInfo;
import com.example.bee2.repository.PostRepository;
import com.example.bee2.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	private static final String[] USER_ROLES  = {"ROLE_USER"};
	private static final String[] ADMIN_ROLES = {"ROLE_USER", "ROLE_ADMIN"};
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
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
		return AuthorityUtils.createAuthorityList(user.getRoles());
	}
	
	public boolean userExists(String username) {
		User user = userRepository.findByName(username);
		
		return user == null;
	}
	
	public void registerUser(Map<String, String> userInformations) {
	  registUser(userInformations.get("username"), Long.parseLong(userInformations.get("age")), userInformations.get("email"), userInformations.get("password"), userInformations.get("location"), USER_ROLES, false);
	}
	
	public void registerAdmin(Map<String, String> userInformations) {
		registUser(userInformations.get("username"), Long.parseLong(userInformations.get("age")), userInformations.get("email"), userInformations.get("password"), userInformations.get("location"), ADMIN_ROLES, true);
	}
	
	private void registUser(String username, Long age, String email, String password, String location, String[] roles, boolean isAdmin) {
		userRepository.save(new User(username, age, email, passwordEncoder.encode(password), location, "/assets/profile_image/default_image.svg", roles, isAdmin));
	}
	
	public void addNewUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
	
	public Collection<User> findAll() {
		return userRepository.findAll();
	}
	
	public Collection<User> findByNameContaining(String name) {
		return userRepository.findByNameContaining(name);
	}
	
	public Collection<User> getMaybeFriends(String name) {
		List<User> maybeFriends = (List<User>) userRepository.getMaybeFriends(name);
		List<User> followings = (List<User>) userRepository.getFollowings(name);
		
		for (User user : followings) {
			maybeFriends.remove(user);
		}

		return maybeFriends;
	}
	
	public Collection<User> getFollowers(String name) {
		return userRepository.getFollowers(name);
	}
	
	public Collection<User> getFollowings(String name) {
		return userRepository.getFollowings(name);
	}
	
	public int getFollowerCount(String name) {
		return userRepository.getFollowerCount(name);
	}
	
	public int getFollowingCount(String name) {
		return userRepository.getFollowingCount(name);
	}
	
	public Collection<Post> findByPosted(String name) {
		return postRepository.findByPosted(name);
	}
	
	public void unlock(String name) {
		User user = userRepository.findByName(name);
		user.setLockout(false);
		user.setFailed(0L);
		userRepository.save(user);
	}
	
	public void lock(String name) {
		User user = userRepository.findByName(name);
		user.setLockout(true);
		userRepository.save(user);
	}
	
	public void follow(String fromUserName, String toUserName) {
	  if (fromUserName.equals(toUserName)) return;
	  
	  User from = userRepository.findByName(fromUserName);
	  User to = userRepository.findByName(toUserName);
	  
	  if (from.noFollowing()) from.createFollowingSet();
	  
	  from.getFollowing().add(to);
	  userRepository.save(from);
	  
	  updatePrincipal(from);
	}
	
	public void followAuto(String fromUserName, String toUserName) {
	  if (fromUserName.equals(toUserName)) return;
	  
	  User from = userRepository.findByName(fromUserName);
      User to = userRepository.findByName(toUserName);
      
      if (from.noFollowing()) from.createFollowingSet();
      
      from.getFollowing().add(to);
      userRepository.save(from);
	}
	
	public void release(String fromUserName, String toUserName) {
	  User from = userRepository.findByName(fromUserName);
	  User to = userRepository.findByName(toUserName);
	  
	  from.getFollowing().remove(to);
	  userRepository.save(from);
	  
	  updatePrincipal(from);
	}
	
	public void updateProfileImage(User user, String filePath) {
		user.setImagePath(filePath);
		userRepository.save(user);
		updatePrincipal(user);
	}
	
	public void updatePrincipal(User user) {
	  UserInfo userInfo = new UserInfo(user, getAuthorities(user));
	  Authentication auth = new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), userInfo.getAuthorities());
	  SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public void loginFailed(String username) {
		Optional<User> userOptional = Optional.ofNullable(userRepository.findByName(username));
		
		userOptional.ifPresent(user -> {
			user.setFailed(user.getFailed() + 1L);
			if (user.getFailed() >= 3) {
				user.setLockout(true);
			}
			userRepository.save(user);
		});
	}
}
