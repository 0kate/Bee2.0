package com.example.bee2.utility;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.example.bee2.entity.Post;

@Component
public class PostComparator implements Comparator<Post> {
	@Override
	public int compare(Post p1, Post p2) {
		return p1.getDate().compareTo(p2.getDate());
	}
}
