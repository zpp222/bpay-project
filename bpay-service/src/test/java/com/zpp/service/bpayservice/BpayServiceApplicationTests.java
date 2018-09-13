package com.zpp.service.bpayservice;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.bpay.serviceI.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zpp.service.bpayservice.kafka.LogDataSend;
import com.zpp.service.bpayservice.redis.RedisHandler;
import com.zpp.service.bpayservice.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BpayServiceApplicationTests {

	@Resource
	private UserServiceImpl userService;

	@Resource
	private LogDataSend logDataSend;
	
	@Resource
	private RedisHandler redisHandler;
	
	@Test
	public void contextLoads() {
		User user = userService.login("zpp");
		assertEquals("18729211089", user.getPhone());
	}
	
	@Test
	public void kafkaTest(){
		User user = new User();
		user.setId("7123");
		user.setName("张鹏鹏");
		user.setPasswd("no");
		user.setPhone("18729211089");
		user.setSex("男");
		logDataSend.sendLog(user.toString());
	}
	
	@Test
	public void redisHandlerTest(){
		long result = redisHandler.bitCount("zpp_t");
		System.out.println("########"+result);
	}

}
