package mts.ftth.vc4.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mts.ftth.vc4.configuration.WLSConfig;

@Repository
public interface SysConfigDAO extends JpaRepository<WLSConfig, String>{

	@Query(value="select * from so_schema.web_logic_conf" , nativeQuery = true)
	public WLSConfig getWLSConfig();
	
}
