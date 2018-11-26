package com.example.bee2.service;

import java.util.Calendar;
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
	
	public void sendMessage(String sender, String reciever, String title, String text) {
	  Message message = new Message(sender, reciever, getDateStr(), title, text);
	  messageRepository.save(message);
	}
	
	private String getDateStr() {
	  Calendar cal = Calendar.getInstance();
      return cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE);
    }
}
