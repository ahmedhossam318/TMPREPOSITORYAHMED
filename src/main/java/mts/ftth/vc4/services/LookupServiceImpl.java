package mts.ftth.vc4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mts.ftth.vc4.models.Fault;
import mts.ftth.vc4.repos.FaultRepo;

@Service
public class LookupServiceImpl implements LookupService{
	@Autowired
	private FaultRepo faultRepo;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fault>GetAllFault(){
		return faultRepo.findAll();
	}
	

}
