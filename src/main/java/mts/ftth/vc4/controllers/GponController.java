package mts.ftth.vc4.controllers;

import java.util.ArrayList;
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
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.Gpon;
import mts.ftth.vc4.models.GponCard;
import mts.ftth.vc4.models.GponCardPort;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.GponService;

@RestController
@RequestMapping("/api/gpon")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class GponController {
	
	private static final Logger logger = LogManager.getLogger(GponController.class);
	
	@Autowired
	GponService gponService;
	@Autowired
	VC4Token vc4Token;

	@GetMapping("/getGponCardList")
	public ResponseEntity<APIResponse> getGponCardList(@RequestParam(value = "GponId") String gponId){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch gpon card list...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = gponService.GetGponCards(token, gponId);
		return res;
		
//		APIResponse response=new APIResponse();
//		try {
//			token = vc4Token.getVc4Token();
//			System.out.println("tt :" +token);
//			if(token.equals("Fail")) {
//				response.setStatus(HttpStatus.REQUEST_TIMEOUT);
//				response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
//				return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
//			}
//			gponCards=gponService.GetGponCards(token, gponId);
//			
//		response.setStatus(HttpStatus.OK);
//		response.setStatusCode(HttpStatus.OK.value());
//		if(gponCards != null)
//			response.setClientMessage("Success");
//		else
//			response.setClientMessage("No object found");
//		response.setBody(gponCards);	
//		logger.info("Request Success.");
//		}
//		catch (Exception e) {
//			logger.info("Request Failed");
//			e.printStackTrace();
//			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//			response.setClientMessage("An error occured while fetching audit data");
//			return new ResponseEntity<APIResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		logger.info("##############################################");
//		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getGponList")
	public ResponseEntity<APIResponse> getAllGpon(@RequestParam(value = "PaginatorStartElement") int paginatorStartElement,
			@RequestParam(value = "PaginatorNumberOfElements") int paginatorNumberOfElements ,@RequestParam(value = "ExchCode") String exchCode){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch gpon list...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = gponService.GetAllGpon(token, paginatorStartElement , paginatorNumberOfElements,exchCode);
		return res;
	}
	
	@GetMapping("/getGponCardPortList")
	public ResponseEntity<APIResponse> getGponCardPortList(@RequestParam(value = "CardId") String cardId){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch gpon card port list...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = gponService.GetGponCardPorts(token, cardId);
		return res;
	}
}
