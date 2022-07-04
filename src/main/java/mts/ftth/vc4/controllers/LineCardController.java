package mts.ftth.vc4.controllers;

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
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.LineCardService;

@RestController
@RequestMapping("/api/LineCard")
@RequiredArgsConstructor
public class LineCardController {
	private static final Logger logger = LogManager.getLogger(GponController.class);
	
	
	@Autowired
	LineCardService linecardService;
	

	@Autowired
	VC4Token vc4Token;
	
	@GetMapping("/getLineCardActiveData")
	public ResponseEntity<APIResponse> getLineCardActiveData(@RequestParam(value = "CityCode") String cityCode,@RequestParam(value = "TelNo") String telNo){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getLineCardActiveData...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = linecardService.getActiveDataLineCard(token, cityCode,telNo);
		return res;
	}
	

	@GetMapping("/getLineCardPassiveData")
	public ResponseEntity<APIResponse> getLineCardPassiveData(@RequestParam(value = "CityCode") String cityCode,@RequestParam(value = "TelNo") String telNo){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getLineCardPassiveData...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = linecardService.getPassiveDataLineCard(token, cityCode,telNo);
		return res;
	}

}
