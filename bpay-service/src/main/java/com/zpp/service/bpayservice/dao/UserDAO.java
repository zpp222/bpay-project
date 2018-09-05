package com.zpp.service.bpayservice.dao;

import org.bpay.serviceI.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zpp.service.bpayservice.mapper.UserMapper;

@Repository
public class UserDAO {

	@Autowired
	private UserMapper userMapper;

	public User selectByName(String name) {
		User user = userMapper.selectByName(name);
		return user;
	}
}
