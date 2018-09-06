package com.zpp.service.bpayservice.repository;
import org.bpay.serviceI.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


@Component
public class ConsumersCollections {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private MongoTemplate mongoTemplate;

	public void insert(User user){
		logger.info("use database {}",mongoTemplate.getDb().getName());
		mongoTemplate.insert(user);
	}
}
