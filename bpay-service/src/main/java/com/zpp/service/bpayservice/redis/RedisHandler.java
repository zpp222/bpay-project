package com.zpp.service.bpayservice.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisHandler {
	private StringRedisTemplate template;
	
	@Autowired
	public RedisHandler(StringRedisTemplate template) {
		this.template = template;
	}
	
	public void put(String key,String value, long milseconds){
		template.opsForValue().set(key, value,milseconds,TimeUnit.MILLISECONDS);
	}
	
	public String get(String key){
		return template.opsForValue().get(key);
	}
}
