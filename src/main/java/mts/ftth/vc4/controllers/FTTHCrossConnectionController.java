package mts.ftth.vc4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.FTTHCrossConnection;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.repos.FTTHCrossConnectionReository;

@RestController
@RequestMapping("/api/FTTHCrossConnection")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class FTTHCrossConnectionController {
	private static final Logger logger = LogManager.getLogger(FTTHCrossConnection.class);
	@Autowired
	FTTHCrossConnectionReository ftthRepo;
	
	@PostMapping("/uploadSheet")
	public ResponseEntity<APIResponse> uploadSheet(@RequestBody List<FTTHCrossConnection> ftthCon) {

   
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to uploadSheet ...");
		logger.info("##############################################");
		for(FTTHCrossConnection c : ftthCon) {
			System.out.println("c.getQueueId()::"+c.getQueueId());
//			c.setQueueId(c.getQueueId());
			ftthRepo.save(c);
		}
		
			response.setStatus(HttpStatus.OK);
			return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
	@GetMapping("/getFTTHCrossList")
	public ResponseEntity<APIResponse> getFTTHCrossList(){
		APIResponse response=new APIResponse();
		logger.info("##############################################");
		logger.info("Client request to fetch getFTTHCrossList...");
		logger.info("##############################################");
		List<FTTHCrossConnection> ftthCon = ftthRepo.findAll();
		response.setStatus(HttpStatus.OK);
		response.setBody(ftthCon);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
		
	}
}
