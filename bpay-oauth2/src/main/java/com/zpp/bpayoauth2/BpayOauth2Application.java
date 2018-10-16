package com.zpp.bpayoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BpayOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(BpayOauth2Application.class, args);
	}
}
