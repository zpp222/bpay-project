package com.zpp.console.bpayconsole.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping("/")
	public String index() {
		return "hello";
	}
}
