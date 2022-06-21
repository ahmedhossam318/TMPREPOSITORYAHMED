package mts.ftth.vc4.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="SC_USERS", schema = "SO_SCHEMA")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@SuperBuilder
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long USER_ID;
	@Column(name="USER_NAME") private String USERNAME;
	private String EMAIL_ADDRESS;
	private String DISPLAY_NAME;
	private String LAST_MODIFIED_BY;
	private Long ORG_UNIT_ID;
	private String USER_PASSWORD;
	private String PASSWORD_ENC;
//	private Integer ENABLED;
//	private Integer ACTIVE;
//	private String USER_TYPE;
//	private String WORKER_ID;
	@Transient
	private String EMP_ORG;
	
//	@Transient
//	private Set<UserDataScope> scopeList;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "SC_USERROLE", schema = "SO_SCHEMA",
				joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "USER_ID"), 
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private Set<Role> ROLES;
	
	// Hibernate mapping is not valid because we need module_id as env param
	/*@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "SC_USER_PERMISSION", schema = "FRM_WFM_SEC",
				joinColumns = @JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME"), 
				inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "PERMISSION_ID"))
	@WhereJoinTable(clause="MODULE_ID = " + @Value("${module.id}"))*/
	@Transient
	private Set<UserPermission> PERMISSIONS;

//	@Transient
//	private Set<UserDataScope> SCOPE;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();

		ROLES.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getROLE_NAME()));        
        });
				
		PERMISSIONS.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getPERMISSION_NAME()));        
        });
		
//		SCOPE.forEach(r -> {
//			System.out.println("value:"+r.getVALUE());
//		});
		
//		setScopeList(SCOPE);
//		
//		
//		getScopeList().forEach(r -> {
//			System.out.println("value getScopeList:"+r.getVALUE());
//		});
//		
//		SCOPE.forEach(r -> {
////			this.scopeList.add( r.getVALUE());
////			authorities.ad
////			scopeList.add r.getVALUE()
////            authorities.add(new SimpleGrantedAuthority(r.getVALUE()));
//			scopeList.add(r.getVALUE());
//        });
        return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

//	@Override
//	public boolean isEnabled() {
//		if(getENABLED() != null)
//			return getENABLED() == 1 ? true : false;
//		
//		return true;
//	}

	@Override
	public String getPassword() {
		return PASSWORD_ENC;
	}

	@Override
	public String getUsername() {
		return USERNAME;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
//	public void setScopeList(Set<UserDataScope> scopes) {
////		scopes.add("x");
//		this.scopeList = scopes;
//	}
//	
//	public Set<UserDataScope> getScopeList(){
//		return scopeList;
//	}
}
