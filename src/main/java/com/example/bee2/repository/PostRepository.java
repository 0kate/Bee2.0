package com.example.bee2.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.bee2.entity.Post;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {
	public Post findByPosted(String Posted);
	public Post findByTitle(String title);
	public Post findByTitleLike(String title);
}
