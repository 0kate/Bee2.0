package com.example.bee2.entity;

import javax.persistence.Column;
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
	@Column(name="sender", nullable=false)
	private String sender;
	@Column(name="reciever", nullable=false)
	private String reciever;
	@Column(name="date", nullable=false)
	private String date;
	@Column(name="title", nullable=false)
	private String title;
	@Column(name="text", nullable=false)
	private String text;
	
	public Message(String sender, String reciever, String date, String title, String text) {
		this.sender = sender; 
		this.reciever = reciever;
		this.date = date;
		this.title = title;
		this.text = text;
	}
}
