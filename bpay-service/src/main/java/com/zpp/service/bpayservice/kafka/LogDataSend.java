package com.zpp.service.bpayservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogDataSend {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private final static String  LOG_TOPIC = "logTopic";

	private final KafkaTemplate<?, Object> kafkaTemplate;

	public LogDataSend(KafkaTemplate<?, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendLog(String data){
		kafkaTemplate.send(LOG_TOPIC, data);
	}
	
}
