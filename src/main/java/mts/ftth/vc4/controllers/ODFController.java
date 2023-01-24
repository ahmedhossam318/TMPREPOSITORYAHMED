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
import mts.ftth.vc4.services.apiInterface.ODFService;

@RestController
@RequestMapping("/api/odf")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class ODFController {
private static final Logger logger = LogManager.getLogger(GponController.class);
	
	@Autowired
	ODFService odfService;
	
	@Autowired
	VC4Token vc4Token;
	
	@GetMapping("/getODFList")
	public ResponseEntity<APIResponse> getODFList(){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getODFList...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = odfService.GetODFList(token);
		return res;
	}
	
	@GetMapping("/getODFListByExch")
	public ResponseEntity<APIResponse> getODFListByExch(@RequestParam(value = "ExchCode") String exchCode){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getODFListByExch...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		System.out.println("exchCode::" +exchCode);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = odfService.GetODFListByExch(token,exchCode);
		return res;
	}
	

	@GetMapping("/getODFPortList")
	public ResponseEntity<APIResponse> getODFPortList(@RequestParam(value = "ODFID") String ODFID){
		String token ="";
		//lw model ok mynf34 service aw DAO aw Bean aw Singleton class y Amor dol t3mlhom auto wire
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getODFPortList...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = odfService.GetODFPortList(token,ODFID);
		return res;
	}
	
	@GetMapping("/getODFListByType")
	public ResponseEntity<APIResponse> getODFListByType(String type){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getODFListByType...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = odfService.GetODFListByType(token,type);
		return res;
	}
	
	
	@GetMapping("/getODFListByPopId")
	public ResponseEntity<APIResponse> getODFListByPopId(String popId){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getODFListByPopId...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = odfService.GetODFListByPopId(token,popId);
		return res;
	}


}
