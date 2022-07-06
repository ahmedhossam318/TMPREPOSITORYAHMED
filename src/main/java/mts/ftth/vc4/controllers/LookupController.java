package mts.ftth.vc4.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.Fault;
import mts.ftth.vc4.models.Gpon;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.CabinetService;
import mts.ftth.vc4.services.LookupService;

@RestController
@RequestMapping("/api/lookup")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class LookupController {
	
	
	private static final Logger logger = LogManager.getLogger(GponController.class);
	
	@Autowired
	CabinetService cabinetService;
	
	@Autowired
	VC4Token vc4Token;
	
	@Autowired 
	LookupService lookupService;
	
	@GetMapping("/getFaultList")
	public ResponseEntity<APIResponse> getFaultList(){
		APIResponse response=new APIResponse();
		List<Fault> faults;
		logger.info("##############################################");
		logger.info("Client request to fetch getFaultList...");
		logger.info("##############################################");
		
		faults = lookupService.GetAllFault();
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		if(faults != null)
			response.setClientMessage("Success");
		else
			response.setClientMessage("No object found");
		response.setBody(faults);	
		logger.info("Request Success.");
		
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/getSplitterTypes")
	public ResponseEntity<APIResponse> getSplitterTypes(){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getSplitterTypes...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.GetSplitterTypes(token);
		return res;
	}

}
