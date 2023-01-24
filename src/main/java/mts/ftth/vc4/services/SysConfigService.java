package mts.ftth.vc4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mts.ftth.vc4.configuration.WLSConfig;
import mts.ftth.vc4.repos.SysConfigDAO;

@Service
public class SysConfigService {

	@Autowired
	SysConfigDAO dao;
	
	public WLSConfig getWLSConfig() {
		return dao.getWLSConfig();
	}
}
