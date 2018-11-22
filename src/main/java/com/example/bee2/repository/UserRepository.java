package com.example.bee2.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bee2.entity.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
	public User findByName(String name);
	
	public Collection<User> findByNameContaining(String name);
}
