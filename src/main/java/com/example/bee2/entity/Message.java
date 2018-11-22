package com.example.bee2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String sender;
	private String reciever;
	private String date;
	private String title;
	private String text;
	
	public Message(String sender, String reciever, String date, String title, String text) {
		this.sender = sender; 
		this.reciever = reciever;
		this.date = date;
		this.title = title;
		this.text = text;
	}
}
