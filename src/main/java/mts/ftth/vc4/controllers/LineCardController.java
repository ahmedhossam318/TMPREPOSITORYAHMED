package mts.ftth.vc4.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.SYS_AUDIT;
import mts.ftth.vc4.models.TBPortRequest;
import mts.ftth.vc4.models.UpLineCardRequest;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.apiInterface.AuditService;
import mts.ftth.vc4.services.apiInterface.LineCardService;

@RestController
@RequestMapping("/api/LineCard")
@RequiredArgsConstructor
public class LineCardController {
	private static final Logger logger = LogManager.getLogger(GponController.class);
	
	
	@Autowired
	LineCardService linecardService;
	

	@Autowired
	VC4Token vc4Token;
	
	@Autowired
	AuditService audService;
	
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
	
	@GetMapping("/getTBListByGponCardPort")
	public ResponseEntity<APIResponse> getTBListByGponCardPort(@RequestParam(value = "GponId") String gponId,@RequestParam(value = "CardId") String cardId,@RequestParam(value = "PortNo") String portNo){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getTBListByGponCardPort...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = linecardService.getTBByGponCardPort(token, gponId,cardId,portNo);
		return res;
	}
	
	@PostMapping("/getTBPotByPassive")
	public ResponseEntity<APIResponse> getTBPotByPassive(@RequestBody TBPortRequest req){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getTBPotByPassive...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = linecardService.getTBPotByPassive(token, req);
		return res;
	}
	
	@PutMapping("/updateFccLineCard")
	public ResponseEntity<APIResponse> updateFccLineCard(@RequestBody UpLineCardRequest req){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch updateFccLineCard...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = linecardService.updateFccLineCard(token, req);
		SYS_AUDIT s = new SYS_AUDIT();
		s.setACTION("UPDATE_FCC_LC");
		s.setACTION_BY(req.getUserName());
		s.setELEMENT_TYPE("FCC_LC");
		s.setELEMENT_VALUE(String.valueOf(req.getId()));
		audService.audit(s);
		return res;
	}

}
