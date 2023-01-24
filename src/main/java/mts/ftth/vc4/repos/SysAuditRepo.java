package mts.ftth.vc4.repos;


import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import mts.ftth.vc4.models.SYS_AUDIT;

public interface SysAuditRepo extends PagingAndSortingRepository<SYS_AUDIT, Long>{
	
	@Query(value = "select * from VC4_ALARM.ELEMENT_AUDIT s where lower (s.ELEMENT_VALUE) like lower (concat(concat('%', :action_data),'%'))\r\n"
			+ "			and lower (s.ACTION_BY) like lower (concat(concat('%', :username),'%')) order by action_id desc",nativeQuery = true)
	List<SYS_AUDIT> GetAllAudits(String action_data,String username,Pageable page);
	
	
	@Query(value = "select * from VC4_ALARM.ELEMENT_AUDIT s where lower (s.ELEMENT_VALUE) like lower (concat(concat('%', :action_data),'%'))\r\n"
			+ "		and lower (s.ACTION_BY) like lower (concat(concat('%', :username),'%'))\r\n"
			+ "		and (TO_CHAR(s.ACTION_DATE,'dd-mm-yyyy')) BETWEEN  nvl(:Faction_date,(TO_CHAR(ACTION_DATE,'dd-mm-yyyy'))) and nvl(:Saction_date,(TO_CHAR(ACTION_DATE,'dd-mm-yyyy'))) order by action_id desc",nativeQuery = true)
	List<SYS_AUDIT> GetAuditTime(String action_data,String username,String Faction_date,String Saction_date,Pageable page);

}
