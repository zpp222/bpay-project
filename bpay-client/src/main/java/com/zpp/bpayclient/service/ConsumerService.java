package com.zpp.bpayclient.service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import net.sf.json.JSONObject;

@Service
public class ConsumerService {

	@Autowired
	@Qualifier("oauth")
	OAuth2RestOperations restTemplate;

	@HystrixCommand(fallbackMethod = "failBack")
	public String exchange(String url, Object request) {
		String result = restTemplate.postForEntity(url, request, String.class).getBody();
		return result;
	}

	public String getExchangeWithBasicAuth(String url, HttpServletRequest request, MultiValueMap<String, String> param)
			throws RestClientException, URISyntaxException {
		HttpEntity<String> httpEntity = new HttpEntity<String>(BasicHeadersUtil.getHeadersWithClientCredentials());
		String result = restTemplate.exchange(new URI(url), HttpMethod.GET, httpEntity, String.class).getBody();
		return result;
	}

	public String getExchangeWithOauth2(String url, HttpServletRequest request, MultiValueMap<String, String> param)
			throws RestClientException, URISyntaxException {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9999/auth/user", String.class);
		return "Success! (" + response.getBody() + ")";
	}

	public String failBack(String url, Object request) {
		System.out.println("error: " + url);
		JSONObject json = new JSONObject();
		json.put("errorcode", "serviceExcp");
		return json.toString();
	}

}
