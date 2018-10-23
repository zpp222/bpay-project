package com.zpp.bpaybatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.zpp.bpaybatch.util.SpringContextUtil;

@SpringBootApplication
public class BpayBatchApplication {

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(BpayBatchApplication.class, args);
		SpringContextUtil.setApplicationContext(app);
		com.alibaba.dubbo.container.Main.main(args);
	}
}
