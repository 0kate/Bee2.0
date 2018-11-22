package com.example.bee2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bee2.entity.Message;
import com.example.bee2.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	public Collection<Message> findByReciever(String reciever) {
		return messageRepository.findByReciever(reciever);
	}
}
