package com.example.bee2.service;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bee2.entity.Post;
import com.example.bee2.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public void addNewPost(String title, String text, String posted, String url) {
		Post post = new Post(title, text, new Date(), posted, url);
		postRepository.save(post);
	}
	
	public Collection<Post> findAll() {
		return (Collection<Post>) postRepository.findAll();
	}
}
