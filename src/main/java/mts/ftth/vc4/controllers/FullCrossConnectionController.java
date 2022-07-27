package mts.ftth.vc4.controllers;

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
import mts.ftth.vc4.services.FullCrossConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/fullCrossConnection")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class FullCrossConnectionController {
	
	private static final Logger logger = LogManager.getLogger(GponController.class);
	
	@Autowired
	FullCrossConnectionService fullCrossConnService;
	
	@Autowired
	VC4Token vc4Token;
	
	
	@GetMapping("/getFullCrossConnection")
	public ResponseEntity<APIResponse> getFullCrossConnection(@RequestParam(value = "PaginatorStartElement") int paginatorStartElement,
			@RequestParam(value = "PaginatorNumberOfElements") int paginatorNumberOfElements ,@RequestParam(value = "ExchCode") String exchCode){
		String token ="";
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch FullCrossConnection...");
		logger.info("##############################################");
		token = vc4Token.token;
		System.out.println("tt :" +token);
		if(token.equals("Fail")) {
			response.setStatus(HttpStatus.REQUEST_TIMEOUT);
			response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
			return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
		}
		ResponseEntity<APIResponse> res = fullCrossConnService.getFullCrossConnection(token, exchCode, paginatorStartElement , paginatorNumberOfElements);
		return res;
	}
	
}
