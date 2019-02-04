package com.example.bee2.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.bee2.entity.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
	public User findByName(String name);
	public Collection<User> findByNameContaining(String name);
	public Collection<User> findAll();
	
	@Query("match (user:User)-[:FOLLOW*2]->(to:User{name:{0}}) return user")
	public Collection<User> getMaybeFriends(String name);
	
	@Query("match (user:User)-[:FOLLOW*1]->(to:User{name:{0}}) return user;")
	public Collection<User> getFollowers(String name);
	
	@Query("match (user:User{name:{0}})-[:FOLLOW*1]->(to:User) return to;")
	public Collection<User> getFollowings(String name);
	
	@Query("match (user:User{name:{0}})-[:FOLLOW*1]->(to:User) return count(to)")
	public int getFollowingCount(String name);
	
	@Query("match (user:User)-[:FOLLOW*1]->(to:User{name:{0}}) return count(to)")
	public int getFollowerCount(String name);
}
