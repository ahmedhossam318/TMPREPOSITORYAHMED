package mts.ftth.vc4.payload.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import mts.ftth.vc4.security.JwtProperties;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {

	 private String token;
	 private String type = JwtProperties.TOKEN_TYPE;
	 private Long id;
	 private String username;
	 private List<String> authorities;
	 private List<String> scopes;

	 public JwtResponse(String accessToken, Long id, String username, List<String> authorities,List<String> scopes) { 
		    this.token = accessToken;
		    this.id = id;
		    this.username = username;
		    this.authorities = authorities;   
		    this.scopes = scopes;
	 }
	 
}
