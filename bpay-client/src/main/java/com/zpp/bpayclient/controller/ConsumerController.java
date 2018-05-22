package com.zpp.bpayclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String login() {
		return restTemplate.getForEntity("http://bpay-console/bpay", String.class).getBody();
	}
}
