package com.zpp.bpayoauth2.securityConfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
@Order(1)
public class BpaySecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().permitAll();
		http.formLogin().successHandler(new SimpleUrlAuthenticationSuccessHandler() {
			private RequestCache requestCache = new HttpSessionRequestCache();

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				SavedRequest savedRequest = requestCache.getRequest(request, response);
				if (savedRequest == null) {
					System.out.println("savedRequest is null ");
					if (request.getSession().getAttribute("callCustomRediretUrl") != null
							&& !"".equals(request.getSession().getAttribute("callCustomRediretUrl"))) {
						String url = String.valueOf(request.getSession().getAttribute("callCustomRediretUrl"));
						super.setDefaultTargetUrl(url);
						super.setAlwaysUseDefaultTargetUrl(true);
						request.getSession().setAttribute("callCustomRediretUrl", "");
					} else {
						super.setDefaultTargetUrl("http://localhost:3333/home");
					}
					super.onAuthenticationSuccess(request, response, authentication);
					return;
				}
				String targetUrlParameter = getTargetUrlParameter();
				if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null
						&& StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
					requestCache.removeRequest(request, response);
					super.setAlwaysUseDefaultTargetUrl(false);
					super.setDefaultTargetUrl("/");
					super.onAuthenticationSuccess(request, response, authentication);
					return;
				}
				clearAuthenticationAttributes(request);
				String targetUrl = savedRequest.getRedirectUrl();
				logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
				if (targetUrl != null && "".equals(targetUrl)) {
					targetUrl = "http://localhost:3333/home";
				}
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
			}

		});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("zpp")
				.password(new BCryptPasswordEncoder().encode("zpp!")).authorities("ROLE_USER", "ROLE_ADMIN");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}