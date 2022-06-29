package mts.ftth.vc4.services;

import mts.ftth.vc4.models.Element;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mts.ftth.vc4.models.FTTHNeOutage;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.repos.AlarmFactoryRepo;
import mts.ftth.vc4.repos.AlarmJobRepository;
import mts.ftth.vc4.repos.FTTHNeOutageRepositry;

@Service
public class NeAlarmJobServiceImpl implements NeAlarmJobService {

	AlarmJobRepository repository;

	@Autowired
	FTTHNeOutageRepositry neRepository;
	@Autowired
	AlarmFactoryRepo aFactory;



	@Override
	public long queryJob(long vc4Id) {
		// TODO Auto-generated method stub

		FTTHNeOutage ne = neRepository.findAllByVc4Id(vc4Id);
		if (ne != null)
			return ne.getFaulty();
		else
			return (long) 0;

	}

	


	@Override
	public String finishJob(String neType,Long vc4Id,String faultyReason, String finishUser
			,String actualClosureDate, String notes,String faultCode){
		// TODO Auto-generated method stub
		FTTHNeOutage ne = neRepository.findAllByVc4Id(vc4Id);
		Element c=null;
		if (ne != null && ne.getFaulty() == 1) {
			ne.setFaulty((long) 0);
			System.out.println("networkElement:"+ne.getVc4Id());
			System.out.println("neType:"+neType);
			neRepository.save(ne);

			repository = ((AlarmJobRepository) aFactory.getNeAlarm(neType));
			
			String found = "NOT_FOUND";
			
			c = (Element) repository.findAllByVc4Id(vc4Id);
			if (c != null) {
				c.setFaultReason(faultyReason);
				c.setFinishUser(finishUser);
				c.setActualRepairDate(actualClosureDate);
				c.setFaultCode(faultCode);
				c.setJobFlag((long) 0);
				c.setNotes(notes);
				
				found = "FOUND";
			}
			repository.save(c);
			if (found.equals("FOUND")) {
				
				return "SUCCESS";
			} else
				return "FAIL";
		}
		return "FAIL";
	}

	@Override
	public String createJob(String neType, Element element) {
		FTTHNeOutage ne = neRepository.findAllByVc4Id(element.getVc4Id());
		if (ne != null) {
			System.out.println("Outage element:"+ne.getVc4Id());
			ne.setFaulty((long) 1);
			neRepository.save(ne);
		
		}else{
//			System.out.println("Outage element "+ne.getVc4Id());
			FTTHNeOutage f = new FTTHNeOutage();
			f.setNeType(neType);
			f.setFaulty((long) 1);
			f.setVc4Id(element.getVc4Id());
			neRepository.save(f);
			
		
		}
		repository = ((AlarmJobRepository) aFactory.getNeAlarm(neType));
		repository.save(element);
		return "success";
	}




	@Override
	public List<FTTHNeOutage> getElementJobs(String type) {
		// TODO Auto-generated method stub
		List<FTTHNeOutage> elementJobs = neRepository.findAllByNeType(type);
		return elementJobs;
	}

}
