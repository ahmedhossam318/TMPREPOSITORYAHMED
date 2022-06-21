package mts.ftth.vc4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.globalControllers.AuthController;
import mts.ftth.vc4.models.User;
import mts.ftth.vc4.payload.request.LoginRequest;
import mts.ftth.vc4.payload.response.JwtResponse;
import mts.ftth.vc4.repos.UserDataScopeRepo;
import mts.ftth.vc4.security.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	@Autowired
	UserDataScopeRepo userScopeRepo;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Override
	public JwtResponse authenticateUser(LoginRequest loginRequest) {
				
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		//User user = (User) authentication.getPrincipal();		
		UserDetails user = (UserDetails) authentication.getPrincipal();
		
//		User u = new User();
		List<String> authorities = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		List<String> scopes =userScopeRepo.getUserDataScopeByUSER_NAME(user.getUsername()).stream().map(item -> item.getVALUE())
		.collect(Collectors.toList());
		
		logger.info("Successful login for user: {}", loginRequest.getUsername());
		return new JwtResponse(jwt, null, user.getUsername(), authorities,scopes);
	}
	
}
