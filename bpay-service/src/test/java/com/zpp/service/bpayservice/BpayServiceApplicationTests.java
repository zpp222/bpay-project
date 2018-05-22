package com.zpp.service.bpayservice;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zpp.service.bpayservice.dto.User;
import com.zpp.service.bpayservice.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BpayServiceApplicationTests {

	@Resource
	private UserService userService;

	@Test
	public void contextLoads() {
		User user = userService.login("zpp");
		assertEquals("18729211089", user.getPhone());
	}

}
