package com.zpp.bpayconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
@EnableOAuth2Sso
public class BpayConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpayConfigServerApplication.class, args);
	}
}
