package com.zpp.bpayclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json" }, consumes = "application/json")
	public String login(@RequestBody String requestJson) {
		HttpHeaders headers = new HttpHeaders();
		// 定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
		headers.setContentType(MediaType.APPLICATION_JSON);
		// RestTemplate带参传的时候要用HttpEntity<?>对象传递
		HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
		String result = restTemplate.postForEntity("http://bpay-console/bpay/login", request, String.class).getBody();
		return result;
	}
}
