package com.zpp.bpayconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BpayConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpayConfigServerApplication.class, args);
	}
}
