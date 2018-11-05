package com.zpp.bpayconfigserver.sso;

//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().permitAll();
		http.antMatcher("/actuator/**").authorizeRequests().anyRequest().hasRole("ENDPOINT_ADMIN");
		http.antMatcher("/bpay**/**").authorizeRequests().anyRequest().permitAll();
//		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests().anyRequest().hasRole("ENDPOINT_ADMIN");
	}

}
