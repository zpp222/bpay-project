package com.zpp.service.bpayservice.service.impl;

import javax.annotation.Resource;

import org.bpay.serviceI.dto.User;
import org.bpay.serviceI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zpp.service.bpayservice.dao.UserDAO;
import com.zpp.service.bpayservice.kafka.LogDataSend;
import com.zpp.service.bpayservice.redis.RedisHandler;

import net.sf.json.JSONObject;

@Service
public class UserServiceImpl implements UserService{

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserDAO userDAO;
	
	@Autowired
	private LogDataSend logDataSend;
	
	@Autowired
	private RedisHandler redisHandler;
	
	@Override
	public User login(String name) {
		logger.info("login from user'name {}.",name);
		User user = null;
		if(null == redisHandler.get(name)){
			user = userDAO.selectByName(name);
			redisHandler.put(name, JSONObject.fromObject(user).toString(), 5*60*1000);
		}else{
			String userStr = redisHandler.get(name);
			user = (User) JSONObject.toBean(JSONObject.fromObject(userStr),User.class);
		}
		logger.info("userDAO.selectByName {} result's {}.",name,null != user?JSONObject.fromObject(user):"");
		
		logDataSend.sendLog(user.toString());
		return user;
	}
}
