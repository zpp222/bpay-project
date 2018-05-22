package com.zpp.service.bpayservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zpp.service.bpayservice.mapper")
public class BpayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpayServiceApplication.class, args);

		com.alibaba.dubbo.container.Main.main(args);
	}
}
