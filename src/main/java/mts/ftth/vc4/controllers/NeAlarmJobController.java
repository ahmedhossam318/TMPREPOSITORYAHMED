package mts.ftth.vc4.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.Element;
import mts.ftth.vc4.models.ElementObject;
import mts.ftth.vc4.models.FTTHNeOutage;
import mts.ftth.vc4.models.NeBoxAlarmJob;
import mts.ftth.vc4.models.NeCabinetAlarmJob;
import mts.ftth.vc4.models.NeGponAlarmJob;
import mts.ftth.vc4.models.NeGponCardAlarmJob;
import mts.ftth.vc4.models.NeGponPortAlarmJob;
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
	
	@PostMapping("/finishJob")
	public ResponseEntity<APIResponse> finishJob(@RequestParam(value = "elementType") String neType,@RequestParam(value = "VC4Id") long vc4Id
			,@RequestParam(value = "faultReason") String faultReason,@RequestParam(value = "finishUser") String finishUser
			,@RequestParam(value = "actualClosureDate") String actualClosureDate,@RequestParam(value = "notes") String notes,
			@RequestParam(value = "faultCode") String faultCode){
		
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to finish job ...");
		logger.info("##############################################");
		
		
			response.setStatus(HttpStatus.OK);
			response.setFinishStatus(jobService.finishJob(neType,vc4Id,faultReason,finishUser,actualClosureDate,notes,faultCode));
			return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
	@GetMapping("/getElementFaulty")
	public ResponseEntity<APIResponse> getElementFaulty(@RequestParam(value = "elementType") String neType){
		
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch element faulty...");
		logger.info("##############################################");
		
		List<FTTHNeOutage> elementJobs =jobService.getElementJobs(neType);
			response.setStatus(HttpStatus.OK);
			response.setElementJobs(elementJobs);
			return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/createElementJob")
	public ResponseEntity<APIResponse> createElementJob(@RequestParam(value = "elementType") String neType
			,@RequestBody ElementObject element) {
//		throws JsonMappingException, JsonProcessingException{
//	}
//			@RequestParam(value = "vc4id") Long vc4id,@RequestParam(value = "promiseDate") String promiseDate,
//			@RequestParam(value = "faultyReason") String faultyReason,
//			@RequestParam(value = "notes") String notes){
		//System.out.println("element VC4ID:"+element.getVc4Id());
       System.out.println("getVc4Id::"+element.getGponElement().getVc4Id());
       
//		ObjectMapper mapper = new ObjectMapper();
//		NeGponAlarmJob result= mapper.readValue(element, NeGponAlarmJob.class);
   
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to create job ...");
		logger.info("##############################################");
		Element e =null;
//		e.setVc4Id((long) 1);
//		return new ResponseEntity<NeGponAlarmJob>((NeGponAlarmJob)element, HttpStatus.OK);
		if (neType.equalsIgnoreCase("GPON")) {
			System.out.println("GPON###########################################:");
//			NeGponAlarmJob j = (NeGponAlarmJob) element;
//			System.out.println("element VC4ID:"+j.getVc4Id());
			e = (NeGponAlarmJob) element.getGponElement();

		} else if (neType.equalsIgnoreCase("CARD")) {
			e = (NeGponCardAlarmJob) element.getCardElement();
		} else if (neType.equalsIgnoreCase("PORT")) {
			e = (NeGponPortAlarmJob) element.getPortElement();

		} else if (neType.equalsIgnoreCase("CABINET")) {
			e = (NeCabinetAlarmJob) element.getCabinetElement();

		} else if (neType.equalsIgnoreCase("BOX")) {
			e = (NeBoxAlarmJob) element.getBoxElement();

		}
		System.out.println("element VC4ID:"+e.getVc4Id());
		String createSts =jobService.createJob(neType,e);
			response.setStatus(HttpStatus.OK);
			response.setCreateStatus(createSts);
			return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

}
