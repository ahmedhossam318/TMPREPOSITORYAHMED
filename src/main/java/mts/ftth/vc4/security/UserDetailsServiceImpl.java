package mts.ftth.vc4.security;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.User;
import mts.ftth.vc4.repos.UserDataScopeRepo;
import mts.ftth.vc4.repos.UserPermissionRepo;
import mts.ftth.vc4.repos.UserRepo;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Value("${module.id}")
	private Long module_id;
	
	@Autowired
	UserPermissionRepo userPermRepo;
	
	@Autowired
	UserDataScopeRepo userScopeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUSERNAME(username);
				//.orElseThrow(() -> new UsernameNotFoundException("User: "+ username +" not found!"));
		
		if(user != null) {
//			user.setEMP_ORG(userRepo.getEmpOrg(user.getWORKER_ID()));
			user.setPERMISSIONS(userPermRepo.getUserPermissionsByUSER_NAME(username, module_id));
			System.out.println("username::"+username);
			System.out.println("username permission::"+user.getPERMISSIONS());
//			user.setSCOPE(userScopeRepo.getUserDataScopeByUSER_NAME(username));
//			user.setScopeList(userScopeRepo.getUserDataScopeByUSER_NAME(username));		
		}else
			throw new UsernameNotFoundException("User: "+ username +" not found!");
		
		return user;
	}

}
