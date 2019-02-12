package com.example.bee2.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.bee2.entity.Post;
import com.example.bee2.entity.User;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {
	public List<Post> findAll();
	public Optional<Post> findById(Long id);
	public void deleteById(Long id);
	public List<Post> findByPosted(String Posted);
	public Post findByTitle(String title);
	public Post findByTitleLike(String title);
	
	@Query("match (p:Post) return max(id(p))")
	public Long searchMaxId();
	
	@Query("match (u:User)-[:OFFER]->(p:Post) where id(p) = {0} return u")
	public Collection<User> getOfferedList(Long id);
	
	@Query("match (u:User{name: {1}})-[o:OFFER]->(p:Post) where id(p) = {0} delete o")
	public void offerDisabled(Long id, String username);
}
