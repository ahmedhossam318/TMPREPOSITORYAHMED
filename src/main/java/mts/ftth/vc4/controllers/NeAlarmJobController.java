package mts.ftth.vc4.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.FTTHNeOutage;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.NeAlarmJobServiceImpl;

@RestController
@RequestMapping("/api/alarm")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class NeAlarmJobController {
	private static final Logger logger = LogManager.getLogger(NeAlarmJobController.class);
	
	
	@Autowired
	NeAlarmJobServiceImpl jobService;
	
	@GetMapping("/getJobList")
	public ResponseEntity<APIResponse> getJobList(@RequestParam(value = "VC4Id") String vc4Id){
		
		return null;
	}
	@GetMapping("/getJobStatus")
	public ResponseEntity<APIResponse> getJobStatus(@RequestParam(value = "VC4Id") long vc4Id){
		
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch job status...");
		logger.info("##############################################");
		
		
			response.setStatus(HttpStatus.OK);
			response.setJobStatus(jobService.queryJob(vc4Id));
			return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/finishJob")
	public ResponseEntity<APIResponse> finishJob(@RequestParam(value = "elementType") String neType,@RequestParam(value = "VC4Id") long vc4Id
			,@RequestParam(value = "faultReason") String faultReason,@RequestParam(value = "finishUser") String finishUser
			,@RequestParam(value = "actualClosureDate") String actualClosureDate,@RequestParam(value = "notes") String notes,
			@RequestParam(value = "faultCode") String faultCode){
		
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch job status...");
		logger.info("##############################################");
		
		
			response.setStatus(HttpStatus.OK);
			response.setFinishStatus(jobService.finishJob(neType,vc4Id,faultReason,finishUser,actualClosureDate,notes,faultCode));
			return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
	@GetMapping("/getElementFaulty")
	public ResponseEntity<APIResponse> getElementFaulty(@RequestParam(value = "elementType") String neType){
		
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch job status...");
		logger.info("##############################################");
		
		List<FTTHNeOutage> elementJobs =jobService.getElementJobs(neType);
			response.setStatus(HttpStatus.OK);
			response.setElementJobs(elementJobs);
			return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

}
