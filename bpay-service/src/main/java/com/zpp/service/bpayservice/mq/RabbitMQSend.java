package com.zpp.service.bpayservice.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSend {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(Object msg){
		amqpTemplate.convertAndSend("someQueue", msg);
	}

}
