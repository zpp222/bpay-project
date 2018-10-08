package com.zpp.bpayoauth2.AuthorizationServerConfigurer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BpayAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	/**
	 * 授权和token的服务
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
	}

	/**
	 * 访问token接口的安全限制
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()");
		security.checkTokenAccess("isAuthenticated()");
		security.allowFormAuthenticationForClients();
	}

	/**
	 * 客户端连接详情
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client").secret("{noop}secret")
				.authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("openid")
		// .redirectUris("http://localhost:3333/").autoApprove(true).accessTokenValiditySeconds(30)
		// .refreshTokenValiditySeconds(1800)
		;
	}

	@Bean
	public TokenStore tokenStore() {
		TokenStore tokenStore = new InMemoryTokenStore();
		return tokenStore;
	}

	@Configuration
	@EnableWebSecurity
	protected static class webSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
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

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/auth").authorizeRequests().anyRequest().authenticated();
		}
	}
}