package com.zpp.bpayclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import net.sf.json.JSONObject;

@Service
public class ConsumerService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "failBack")
	public String exchange(String url,Object request){
		String result = restTemplate.postForEntity(url, request, String.class).getBody();
		return result;
	}
	
	@HystrixCommand(fallbackMethod = "failBack")
	public String exchangeWithAuth(String url,Object request){
		String result = "";
		return result;
	}
	
	public String failBack(String url,Object request) {
		System.out.println("error: "+url);
		JSONObject json = new JSONObject();
		json.put("errorcode", "serviceExcp");
        return json.toString();
    }

}
