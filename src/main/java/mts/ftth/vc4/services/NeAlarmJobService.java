package mts.ftth.vc4.services;


import mts.ftth.vc4.models.Element;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import mts.ftth.vc4.payload.response.APIResponse;


public interface NeAlarmJobService {
	
//	public List<NeAlarmJob> getAllJob(String neType,String vc4Id);
	
	public long queryJob(long vc4Id);
	
	public ResponseEntity<APIResponse> createJob(String neType,Element element);
	
	public String finishJob(String neType,Long vc4Id,String faultyReason, String finishUser
			,String actualClosureDate, String notes,String faultCode);
}
