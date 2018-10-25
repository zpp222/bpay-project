package com.zpp.bpayclient.sso;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig {
	
	@Bean("oauth")
	public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
		return factory.getUserInfoRestTemplate();
	}

}
