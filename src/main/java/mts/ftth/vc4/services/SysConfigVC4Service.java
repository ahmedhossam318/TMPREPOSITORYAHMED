package mts.ftth.vc4.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import mts.ftth.vc4.models.SYS_CONFIG;

public interface SysConfigVC4Service {
	
	public List<SYS_CONFIG> GetAllSysConfigVc4();
	
}
