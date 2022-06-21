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
import mts.ftth.vc4.services.CabinetService;
import mts.ftth.vc4.services.GponService;

@RestController
@RequestMapping("/api/cabinet")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class CabinetController {
private static final Logger logger = LogManager.getLogger(GponController.class);
	
	@Autowired
	CabinetService cabinetService;
	
	@Autowired
	VC4Token vc4Token;
	
	@GetMapping("/getExchCabinetList")
	public ResponseEntity<APIResponse> getexchCabinetList(@RequestParam(value = "ExchCode") String exchCode){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getExchCabinetList...");
		logger.info("##############################################");
		token = vc4Token.getVc4Token();
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.GetExchCabList(token, exchCode);
		return res;
	}
	
	@GetMapping("/getCabSplitterList")
	public ResponseEntity<APIResponse> getCabSplitterList(@RequestParam(value = "cabinetid") String cabId){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getCabSplitterList...");
		logger.info("##############################################");
		token = vc4Token.getVc4Token();
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.GetCabSplitterList(token, cabId);
		return res;
	}
	
	@GetMapping("/getCabTBoxList")
	public ResponseEntity<APIResponse> getCabTBoxList(@RequestParam(value = "cabinetid") String cabId){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getCabTBoxList...");
		logger.info("##############################################");
		token = vc4Token.getVc4Token();
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.GetCabTBoxList(token, cabId);
		return res;
	}
}
