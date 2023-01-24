package mts.ftth.vc4.services;

import java.util.List;

import mts.ftth.vc4.services.apiInterface.AuditService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.SYS_AUDIT;
import mts.ftth.vc4.repos.SysAuditRepo;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

	private final SysAuditRepo sysAuditRepo;
	
	@Override
	public void audit(SYS_AUDIT aud) {
		sysAuditRepo.save(aud);
	}

	@Override
	public List<SYS_AUDIT> GetAllAudits(String action_details, String username, Pageable page) {
		return sysAuditRepo.GetAllAudits(action_details, username, page);
	}

	@Override
	public List<SYS_AUDIT> GetAuditTime(String action_details, String username, String Faction_date,
			String Saction_date, Pageable page) {
		return sysAuditRepo.GetAuditTime(action_details, username, Faction_date, Saction_date, page);
	}

}
