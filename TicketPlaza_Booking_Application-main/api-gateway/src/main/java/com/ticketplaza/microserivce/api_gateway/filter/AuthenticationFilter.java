package com.ticketplaza.microserivce.api_gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
import com.ticketplaza.microserivce.api_gateway.util.JWTUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private JWTUtil jwtUtil;
	
	Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

//	@Autowired
//	JWTUtil jwtUtil;
//
//	@Override
//	public int getOrder() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		// TODO Auto-generated method stub
//		String token = exchange.getRequest().getHeaders().getFirst("Authorization");
//		String userName;
//
//		if (token == null || !token.startsWith("Bearer ")) {
//			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//			return exchange.getResponse().setComplete();
//		}
//
//		String authHeader = token.substring(7);
//		userName = jwtUtil.extractUserName(authHeader);
//		if (userName != null && jwtUtil.isTokenValid(authHeader, userName)) {
//			exchange.getRequest().mutate().header("x-Authenticated-User", userName).build();
//		} else {
//			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//			return exchange.getResponse().setComplete();
//		}
//
//		return chain.filter(exchange);
//
//	}

	public AuthenticationFilter() {
		super(Config.class);
		
	}

	public static class Config {
		public Config() {
			
		}
	}

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return (exchange, chain) -> {
			String token = exchange.getRequest().getHeaders().getFirst("Authorization");
			String userName;
			log.info("Authorization token" + token);
			if (token == null || !token.startsWith("Bearer ")) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}

			String authHeader = token.substring(7);
			log.info(authHeader);
			userName = jwtUtil.extractUserName(authHeader);
			log.info(userName);
			if (userName != null && jwtUtil.isTokenValid(authHeader, userName)) {
				exchange.getRequest().mutate().header("x-Authenticated-User", userName).build();
			} else {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}

			return chain.filter(exchange);
		};
	}

}
