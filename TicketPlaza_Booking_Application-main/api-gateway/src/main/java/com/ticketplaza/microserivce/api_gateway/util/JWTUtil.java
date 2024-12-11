package com.ticketplaza.microserivce.api_gateway.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	@Value("${jwt.secretkey}")
	String secretKey;
//	@Value("{jwt.expireTime}")
	int jwtExpirationms = 3600000;
	
	boolean flag =false;

	public String generateToken(String userName) {
		return Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + jwtExpirationms))
				.setIssuedAt(new Date()).signWith(key()).compact();
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	public String extractUserName(String token) {
		String uname=getAllClaim(token).getSubject();
		return uname;

	}

	private Claims getAllClaim(String token) {

		return Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public boolean isTokenValid(String token, String userDetails) {
		
		String userName=extractUserName(token);
		flag=userName.equals(userDetails) && isTokenExpired(token);
//		System.out.println(flag);
		return userName.equals(userDetails) && isTokenExpired(token);
		
	}

	private boolean isTokenExpired(String token) {

//		return CurrentDate().before(expirationTime(token));
		flag=expirationTime(token).before(currentDate());
		System.out.println("flag is " + flag);
		return currentDate().before(expirationTime(token));
	}

	private Date expirationTime(String token) {

		return getAllClaim(token).getExpiration();
	}
	public Date currentDate() {
		Date date=new Date();
//		System.out.println(date);
		return date;
	}
}
