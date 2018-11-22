package com.example.bee2.service;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bee2.entity.Post;
import com.example.bee2.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public void addNewPost(String title, String text, String posted) {
		Post post = new Post(title, text, getDateStr(), posted);
		postRepository.save(post);
	}
	
	public Collection<Post> getAllPost() {
		return (Collection<Post>) postRepository.findAll();
	}

	private String getDateStr() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE);
	}
}
