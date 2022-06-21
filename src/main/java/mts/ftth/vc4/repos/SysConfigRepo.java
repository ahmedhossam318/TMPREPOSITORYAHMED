package mts.ftth.vc4.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mts.ftth.vc4.configuration.WLSConfig;
import mts.ftth.vc4.models.SYS_CONFIG;

@Repository
public interface SysConfigRepo extends JpaRepository<SYS_CONFIG, String>{
	@Query(value = "select ID,VC4_USERNAME,VC4_PASSWORD,VC4_URL from VC4_ALARM.sys_config where ROWNUM = 1 \r\n ",nativeQuery = true)
	List<SYS_CONFIG> GetAllSysConfig();
	
	
}
