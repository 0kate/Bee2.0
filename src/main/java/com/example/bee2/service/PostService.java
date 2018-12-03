package com.example.bee2.service;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bee2.entity.Post;
import com.example.bee2.entity.User;
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
	
	public Collection<Post> findAll() {
		return (Collection<Post>) postRepository.findAll();
	}
	
	public Post findById(Long id) {
		Optional<Post> postOptional = postRepository.findById(id);
		if (postOptional.isPresent()) {
			return postOptional.get();
		} else {
			return null;
		}
	}
	
	public Long searchMaxId() {
		return postRepository.searchMaxId();
	}
	
	public void offer(String username, Long postId) {
		User user = userRepository.findByName(username);
		
		Post post = findById(postId);
	
		post.getOfferedUser().add(user);
		
		postRepository.save(post);
	}
}
