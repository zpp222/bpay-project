package com.zpp.service.bpayservice.mapper;

import org.bpay.serviceI.dto.User;

public interface UserMapper {
	public User selectByName(String name);
}
