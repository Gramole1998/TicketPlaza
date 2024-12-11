package com.api.ticketsplaza.utility;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	@Value("${jwt.secretkey}")
	String secretKey;
//	@Value("{jwt.expireTime}")
	int jwtExpirationms = 3600000;


	public String generateToken(String userName) {
		return Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + jwtExpirationms))
				.setIssuedAt(new Date()).signWith(key()).compact();
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	public String extractUserName(String token) {
		String uname=getAllClaim(token).getSubject();
		System.out.println(uname);
		return uname;

	}

	private Claims getAllClaim(String token) {

		return Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public boolean validateUser(String token, UserDetails userDetails) {
		String userName=extractUserName(token);
		return userName.equals(userDetails.getUsername()) && isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {

//		return CurrentDate().before(expirationTime(token));
		return expirationTime(token).before(new Date());
	}

	private Date expirationTime(String token) {

		return getAllClaim(token).getExpiration();
	}

}
