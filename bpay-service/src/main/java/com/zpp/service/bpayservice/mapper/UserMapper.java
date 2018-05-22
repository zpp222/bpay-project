package com.zpp.service.bpayservice.mapper;

import com.zpp.service.bpayservice.dto.User;

public interface UserMapper {
	public User selectByName(String name);
}
