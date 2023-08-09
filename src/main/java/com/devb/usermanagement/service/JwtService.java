
package com.devb.usermanagement.service;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "xMMJh6KtvDhBDDPoZm+KvIZNjk0vZ2Pv1PAfgrQXKWvQYPPbQnj5BZX0HOAEvYl3";

	
	public String extractUserEmail(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	
	public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
		 final Claims claims = extractAllClaims(token);
		 return claimResolver.apply(claims);
	}
	
	public String generateToken(String email) {
		return generateToken(new HashMap<>(), email);
	}
	
	public String generateToken(
			Map<String, Object> extraClaims, 
			String email) {
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 *24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	  public boolean isTokenValid(String token, UserDetails userDetails) {
		    final String username = extractUserEmail(token);
		    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
		  }

	private boolean isTokenExpired(String token) {
		    return extractExpiration(token).before(new Date());
		  }
	 private Date extractExpiration(String token) {
		    return extractClaims(token, Claims::getExpiration);
		  }	  
	
	public Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	private java.security.Key getSignInKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}
}
