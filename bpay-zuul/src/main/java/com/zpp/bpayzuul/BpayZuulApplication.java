package com.zpp.bpayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableOAuth2Sso
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class BpayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpayZuulApplication.class, args);
	}
}
