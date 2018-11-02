package com.zpp.console.bpayconsole.controller;

import org.bpay.serviceI.dto.User;
import org.bpay.serviceI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import net.sf.json.JSONObject;

@RestController
public class LoginController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Reference(timeout=30000,lazy=true) // 超时时间30秒(单位:ms)
	UserService userService;
	
	@RequestMapping("/**")
	public String index() {
		return "404 bad request!";
	}
	
	@RequestMapping(value={"/login"},method=RequestMethod.POST,produces = {"application/json"},consumes="application/json")
	public String login(@RequestBody User user){
		JSONObject json = new JSONObject();
		logger.info("开始登陆请求:{}",JSONObject.fromObject(user));
		User user3= userService.login(user.getName());
		if(null == user3){
			json.put("errorcode", "9999999");
		}else{
			json.put("errorcode", "0000000");
		}
		logger.info("请求结果 :{}",JSONObject.fromObject(user3));
		return json.toString();
	}
}
