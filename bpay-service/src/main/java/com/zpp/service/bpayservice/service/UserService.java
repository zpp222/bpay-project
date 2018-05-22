package com.zpp.service.bpayservice.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpp.service.bpayservice.dao.UserDAO;
import com.zpp.service.bpayservice.dto.User;

@Service
public class UserService {

	@Resource
	private UserDAO userDAO;

	public User login(String name) {
		User user = null;
		user = userDAO.selectByName(name);
		return user;
	}

}
