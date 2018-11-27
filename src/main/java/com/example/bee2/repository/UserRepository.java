package com.example.bee2.repository;

import java.util.Collection;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.bee2.entity.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
	public User findByName(String name);
	public Collection<User> findByNameContaining(String name);
	public Collection<User> findAll();
}
