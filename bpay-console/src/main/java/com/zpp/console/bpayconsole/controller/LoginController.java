package com.zpp.console.bpayconsole.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class LoginController {

	@RequestMapping("/")
	public String index() {
		return "hello";
	}
}
