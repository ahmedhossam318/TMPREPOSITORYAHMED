package mts.ftth.vc4.controllers;

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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.FinishObject;
import mts.ftth.vc4.models.TBPortRequest;
import mts.ftth.vc4.models.UpBox;
import mts.ftth.vc4.models.UpBoxSplitter;
import mts.ftth.vc4.models.UpSplitter;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.CabinetService;
import mts.ftth.vc4.services.GponService;
import mts.ftth.vc4.services.LineCardService;

@RestController
@RequestMapping("/api/cabinet")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class CabinetController {
private static final Logger logger = LogManager.getLogger(GponController.class);
	
	@Autowired
	CabinetService cabinetService;
	
	@Autowired
	LineCardService linecardService;
	
	@Autowired
	VC4Token vc4Token;
	
	@GetMapping("/getExchCabinetList")
	public ResponseEntity<APIResponse> getexchCabinetList(@RequestParam(value = "ExchCode") String exchCode){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getExchCabinetList...");
		logger.info("##############################################");
		token = vc4Token.token;
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
		token = vc4Token.token;
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
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.GetCabTBoxList(token, cabId);
		return res;
	}
	
	@GetMapping("/getSpliierPortList")
	public ResponseEntity<APIResponse> getSpliierPortList(@RequestParam(value = "SplitterId") String splitterId){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getCabTBoxList...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.GetSplitterPortList(token, splitterId);
		return res;
	}
	
	@GetMapping("/getCabinetAlarmJobs")
	public ResponseEntity<APIResponse> getCabinetAlarmJobs(@RequestParam(value = "Vc4Id") Long vc4Id){
		ResponseEntity<APIResponse> res = cabinetService.GetCabinetAlarmJobs(vc4Id);
		return res;
	}
	
	
	@GetMapping("/getBoxAlarmJobs")
	public ResponseEntity<APIResponse> getBoxAlarmJobs(@RequestParam(value = "Vc4Id") Long vc4Id){
		ResponseEntity<APIResponse> res = cabinetService.GetCabinetBoxAlarmJobs(vc4Id);
		return res;
	}
	
	
	@PostMapping("/updateFCCSplitter")
	public ResponseEntity<APIResponse> updateSplitter(@RequestBody UpSplitter splitter){
		String token ="";
		System.out.println("citty : "+splitter.getEXCH_CODE());
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch updateSplitter...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.UpdateSplitter(token, splitter);
		return res;
	}
	
	
	@PostMapping("/updateFCCBox")
	public ResponseEntity<APIResponse> updateFCCBox(@RequestBody UpBox box){
		String token ="";
		APIResponse response=new APIResponse();
		System.out.println("type : "+box.getBOX_ID());
		logger.info("##############################################");
		logger.info("Client request to fetch updateFCCBox...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.UpdateBox(token, box);
		return res;
	}
	
	
	@GetMapping("/getFreeSpliierPortList")
	public ResponseEntity<APIResponse> getFreeSpliierPortList(@RequestParam(value = "SplitterId") String splitterId){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getFreeSpliierPortList...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.GetSplitterFreePortList(token, splitterId);
		return res;
	}
	
	@PostMapping("/updateFCCBoxSplitter")
	public ResponseEntity<APIResponse> updateFCCBoxSplitter(@RequestBody UpBoxSplitter boxSplitter){
		String token ="";
//		System.out.println("citty : "+splitter.getEXCH_CODE());
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch updateFCCBoxSplitter...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = cabinetService.UpdateBoxSplitter(token, boxSplitter);
		return res;
	}
	
	
	@PostMapping("/getBoxTerminals")
	public ResponseEntity<APIResponse> getBoxTerminals(@RequestBody TBPortRequest req){
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
	
}
