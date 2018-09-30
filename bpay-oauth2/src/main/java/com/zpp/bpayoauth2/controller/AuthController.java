package com.zpp.bpayoauth2.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@RequestMapping("/user")
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;

		// return principal;
	}

	@GetMapping("/open/test")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public @ResponseBody String test() {
		return "oauth succ~";
	}

	@GetMapping("/open/test2")
	@PreAuthorize("hasAnyRole('ROLE_USER2')")
	public @ResponseBody String test2() {
		return "oauth succ~";
	}
}
