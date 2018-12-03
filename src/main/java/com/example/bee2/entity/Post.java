package com.example.bee2.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Post {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String text;
	private Date date;
	private String posted;
	private String url;
	
	@Relationship(type="OFFER", direction=Relationship.INCOMING)
	private Set<User> offeredUser = new HashSet<>();
	
	public Post(String title, String text, Date date, String posted, String url) {
		this.title = title;
		this.text = text;
		this.date = date;
		this.posted = posted;
		this.url = url;
	}
}
