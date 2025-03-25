package com.himedia.serviceimpl;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println("Received from Redis: " + message.toString());
		
	}
	

}
