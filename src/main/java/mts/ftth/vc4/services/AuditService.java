package mts.ftth.vc4.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import mts.ftth.vc4.models.SYS_AUDIT;

public interface AuditService {

	public void audit(SYS_AUDIT aud);
	
	public List<SYS_AUDIT>GetAllAudits(String action_details,String username,Pageable page);
	
	public List<SYS_AUDIT> GetAuditTime(String action_details,String username,String Faction_date,String Saction_date,Pageable page);
	
}
