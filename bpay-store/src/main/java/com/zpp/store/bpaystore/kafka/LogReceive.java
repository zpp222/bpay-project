package com.zpp.store.bpaystore.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.zpp.store.bpaystore.repository.ConsumersCollections;

@Component
public class LogReceive {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ConsumersCollections consumersCollections;
	
	@KafkaListener(topics = "logTopic")
	public void processMessage(String content) {
		logger.info("receive msg from logTopic : {}",content);
		try {
			consumersCollections.insertContentForUser(content);
		} catch (Exception e) {
			logger.error("invalid data for : {}",content);
		}
	}
}
