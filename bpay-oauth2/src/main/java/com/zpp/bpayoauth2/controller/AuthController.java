package com.zpp.bpayoauth2.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@RequestMapping("/open/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@GetMapping("/open/test")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public @ResponseBody String test() {
		return "oauth succ~";
	}

	@GetMapping("/basic/test2")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public @ResponseBody String test2() {
		return "oauth succ~";
	}
}
