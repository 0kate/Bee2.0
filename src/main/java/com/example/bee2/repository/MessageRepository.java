package com.example.bee2.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bee2.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	public Collection<Message> findByReciever(String reciever);
}
