package mts.ftth.vc4.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mts.ftth.vc4.models.UserDataScope;

public interface UserDataScopeRepo extends JpaRepository<UserDataScope, Long>{
	@Query(value="SELECT * FROM so_schema.SC_USER_SCOPE WHERE USER_NAME = ?1 and CODE = 'EXCH_CODE'", nativeQuery = true)
	public Set<UserDataScope> getUserDataScopeByUSER_NAME(String USER_NAME);
}
