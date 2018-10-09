package com.zpp.bpayclient.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsoController {
	
	@GetMapping("/test")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String test() {
		System.out.println("#client test...");
		return "test";
	}

	@GetMapping("/test2")
	@PreAuthorize("hasAnyRole('ROLE_USER2')")
	public String test2() {
		System.out.println("#client test2...");
		return "test2";
	}

	@GetMapping("/home")
	public String home() {
		return "home!";
	}
}
