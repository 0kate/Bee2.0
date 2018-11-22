package com.example.bee2.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

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
	private String date;
	private String posted;
	private String url;
	
	public Post(String title, String text, String date, String posted, String url) {
		this.title = title;
		this.text = text;
		this.date = date;
		this.posted = posted;
		this.url = url;
	}
}
