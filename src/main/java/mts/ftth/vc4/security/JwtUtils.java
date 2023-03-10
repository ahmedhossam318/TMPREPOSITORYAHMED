package mts.ftth.vc4.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import mts.ftth.vc4.models.User;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	public String generateJwtToken(Authentication authentication) {
		
		//User user = (User) authentication.getPrincipal();
		
		UserDetails user = (UserDetails) authentication.getPrincipal();
	    
		
		
		return Jwts.builder()
				.setSubject((user.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + JwtProperties.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, JwtProperties.SECRET)
				.compact();
		
	}
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token).getBody().getSubject();
	}

}
