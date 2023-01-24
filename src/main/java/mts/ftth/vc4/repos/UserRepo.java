package mts.ftth.vc4.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;

import mts.ftth.vc4.models.User;

public interface UserRepo extends JpaRepository<User, Long>{

	@Query(value="SELECT c FROM User c WHERE upper(c.USERNAME) = upper(?1)", nativeQuery = false)
	User findByUSERNAME(String USERNAME);

	Boolean existsByUSERNAME(String USERNAME);
	
	@Query(value = "select org_role from MTS_WFM_2017.wf_emp_role where emp_role_id=:Worker_id",nativeQuery = true)
	String getEmpOrg(String Worker_id);
	
}
