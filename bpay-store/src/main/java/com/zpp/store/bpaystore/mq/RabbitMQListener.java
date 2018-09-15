package com.zpp.store.bpaystore.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RabbitListener(queues = "someQueue")
	public void processMessage(String content) {
		logger.info("receive msg from someQueue : {}",content);
	}
}
