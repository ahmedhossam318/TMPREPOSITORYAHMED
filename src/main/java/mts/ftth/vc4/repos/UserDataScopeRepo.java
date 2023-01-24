package mts.ftth.vc4.repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mts.ftth.vc4.models.UserDataScope;

public interface UserDataScopeRepo extends JpaRepository<UserDataScope, Long>{
	@Query(value="SELECT distinct VALUE FROM so_schema.SC_USER_SCOPE WHERE upper(USER_NAME) = upper(?1) and CODE = 'EXCH_CODE'", nativeQuery = true)
	public List<String> getUserDataScopeByUSER_NAME(String USER_NAME);
}
