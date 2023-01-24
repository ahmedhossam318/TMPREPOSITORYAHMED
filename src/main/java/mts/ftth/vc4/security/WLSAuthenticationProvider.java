package mts.ftth.vc4.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import mts.ftth.vc4.repos.UserPermissionRepo;
import mts.ftth.vc4.repos.UserRepo;
import mts.ftth.vc4.services.SysConfigService;

@Component
public class WLSAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private SysConfigService service;
	
	WLSJmxInterface wlsAuth = new WLSJmxInterface();
	
	@Value("${module.id}")
	private Long MODULE_ID;
	
	private UserRepo userRepository;
	private UserPermissionRepo userPermRepo;
	
	public WLSAuthenticationProvider(UserRepo userRepository, UserPermissionRepo userPermRepo) {
		this.userRepository = userRepository;
		this.userPermRepo = userPermRepo;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
      //  wlsAuth.postConstruct(service.getWLSConfig());
        
     //   if (wlsAuth.changeUserPassword(name, password, password)) {
        	if(1==1){
        	mts.ftth.vc4.models.User user = this.userRepository.findByUSERNAME(name);
    		if(user != null) {
    			
    			user.setPERMISSIONS(userPermRepo.getUserPermissionsByUSER_NAME(name, MODULE_ID));
    			
    			//UserPrincipal userPrincipal = new UserPrincipal(user);
    			
    			UserDetails principal = new User(name, password, user.getAuthorities());
            	
            	System.out.println("Auth success");
                return new UsernamePasswordAuthenticationToken(principal, password, user.getAuthorities());
    		} 
    		else {
    			UserDetails principal = new User(name, password, new ArrayList<>());
            	
            	System.out.println("Auth success");
                return new UsernamePasswordAuthenticationToken(principal, password, new ArrayList<>());
    		}
        	
        	
        } 
        else {
            return null;
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
