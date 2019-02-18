package com.example.bee2.service;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bee2.entity.Post;
import com.example.bee2.entity.User;
import com.example.bee2.exception.PostNotFoundException;
import com.example.bee2.repository.PostRepository;
import com.example.bee2.repository.UserRepository;

@Service
public class PostService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	public void addNewPost(String title, String text, String posted, String url) {
		Post post = new Post(title, text, new Date(), posted, url);
		postRepository.save(post);
	}
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	
	public Collection<Post> findAll() {
		return (Collection<Post>) postRepository.findAll();
	}
	
	public Post findById(Long id) throws PostNotFoundException {
		Optional<Post> postOptional = postRepository.findById(id);
		postOptional.orElseThrow(() -> new PostNotFoundException());
		return postOptional.get();
	}
	
	public Long searchMaxId() {
		return postRepository.searchMaxId();
	}
	
	public void offer(String username, Long postId) {
		User user = userRepository.findByName(username);
		
		Post post = null;
		try {
			post = findById(postId);
		} catch (PostNotFoundException e) {
		}
	
		post.getOfferedUser().add(user);
		
		postRepository.save(post);
	}
	
	public Collection<User> getOfferedList(Long postId) {
		return postRepository.getOfferedList(postId);
	}
	
	public boolean isOffered(Long id, String username) {
		User user = userRepository.findByName(username);
		Collection<User> offeredList = postRepository.getOfferedList(id);
		
		for (User u : offeredList) {
			if (u.getName().equals(user.getName())) return true;
		}
		
		return false;
	}
	
	public void offerDisabled(Long postId, String username) {
		postRepository.offerDisabled(postId, username);
	}
}
