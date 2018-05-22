package com.zpp.console.bpayconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BpayConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpayConsoleApplication.class, args);
	}
}
