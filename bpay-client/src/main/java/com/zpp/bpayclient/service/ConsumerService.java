package com.zpp.bpayclient.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import net.sf.json.JSONObject;

@Service
public class ConsumerService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OAuth2ClientContext context;

	@Autowired
	OAuth2ProtectedResourceDetails resource;

	@HystrixCommand(fallbackMethod = "failBack")
	public String exchange(String url, Object request) {
		String result = restTemplate.postForEntity(url, request, String.class).getBody();
		return result;
	}

	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	private static HttpHeaders getHeadersWithClientCredentials() {
		String plainClientCredentials = "zpp:zpp!";
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

		HttpHeaders headers = getHeaders();
		headers.add("Authorization", "Basic " + base64ClientCredentials);
		return headers;
	}

	public String getExchangeWithAuth(String url, HttpEntity<String> request)
			throws RestClientException, URISyntaxException {
		System.out.println("##############");
		System.out.println(resource.getAccessTokenUri());
		System.out.println(resource.getTokenName());
		System.out.println(resource.getClientId());
		System.out.println(context.getAccessToken().getValue());
		System.out.println(context.getAccessTokenRequest().getAuthorizationCode());
		OAuth2RestOperations op = new OAuth2RestTemplate(resource, context);
		HttpEntity<String> request11 = new HttpEntity<String>(getHeadersWithClientCredentials());
		String result = op.exchange(new URI(url), HttpMethod.GET, request11, String.class).getBody();
		return result;
	}

	public String failBack(String url, Object request) {
		System.out.println("error: " + url);
		JSONObject json = new JSONObject();
		json.put("errorcode", "serviceExcp");
		return json.toString();
	}

}
