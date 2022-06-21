package mts.ftth.vc4.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mts.ftth.vc4.models.UserPermission;

public interface UserPermissionRepo extends JpaRepository<UserPermission, Long>{

	@Query(value="SELECT * FROM so_schema.SC_USER_PERMISSION WHERE USER_NAME = ?1 and MODULE_ID = ?2", nativeQuery = true)
	public Set<UserPermission> getUserPermissionsByUSER_NAME(String USER_NAME, Long MODULE_ID);
	
}
