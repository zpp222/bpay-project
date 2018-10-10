package com.zpp.bpayclient.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.zpp.bpayclient.service.ConsumerService;

@RestController
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = "application/json")
	public String login(@RequestBody String requestJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
		String result = consumerService.exchange("http://bpay-console/bpay/login", request);
		return result;
	}

	@RequestMapping(value = "/auth1", method = RequestMethod.GET)
	public String oauth(@RequestHeader HttpHeaders headers,HttpServletRequest httpRequest) throws RestClientException, URISyntaxException {
		HttpEntity<String> request = new HttpEntity<String>(headers);
//		System.out.println(headers);
		String result = consumerService.getExchangeWithAuth("http://localhost:9999/auth/open/test", request);
		return result;
	}
}
