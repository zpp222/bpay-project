package com.zpp.store.bpaystore.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class ConsumersCollections {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String COLLECTIONNAME_USER = "user";

	@Autowired
	private MongoTemplate mongoTemplate;

	public void insertContentForUser(String content) {
		logger.info("use database {}", mongoTemplate.getDb().getName());
		JSONObject json = JSONObject.fromObject(content);
		mongoTemplate.insert(json,COLLECTIONNAME_USER);
	}
}
