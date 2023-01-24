package mts.ftth.vc4.globalControllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.AuditSearch;
import mts.ftth.vc4.models.SYS_AUDIT;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.security.UserDetailsServiceImpl;
import mts.ftth.vc4.services.apiInterface.AuditService;


@RestController
@RequestMapping("/api/Audit")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class AuditController {

	@Autowired
	AuditService auditService;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	private static final Logger logger = LogManager.getLogger(AuditController.class);
	@PostMapping("/getAllAudit")
	public ResponseEntity<APIResponse> getAllAudit(@RequestBody AuditSearch A,@RequestParam(value = "PageNo") int PageNo,
			@RequestParam(value = "Size") int Size,Authentication auth)
	{
		logger.info("##############################################");
		logger.info("Client request to fetch audit data...");
		logger.info("##############################################");
		APIResponse response=new APIResponse();
		try {
			System.out.println("in getaudit");
			System.out.println("user:"+auth.getName() );
			System.out.println("A.getSACTION_DATE()::"+A.getSACTION_DATE()+"user::"+A.getACTION_DATA());
			System.out.println(A.toString());
			List<SYS_AUDIT> objects;
			Pageable page = PageRequest.of(PageNo-1, Size);
			if(A.getSACTION_DATE()==null && A.getFACTION_DATE()==null)
				objects=auditService.GetAllAudits(A.getACTION_DATA(), A.getUSERNAME(), page);
			else
				objects=auditService.GetAuditTime(A.getACTION_DATA(), A.getUSERNAME(), A.getFACTION_DATE(), A.getSACTION_DATE(), page);
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		if(objects != null)
			response.setClientMessage("Success");
		else
			response.setClientMessage("No object found");
		response.setBody(objects);	
		logger.info("Request Success.");
		}
		catch (Exception e) {
			logger.info("Request Failed");
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setClientMessage("An error occured while fetching audit data");
			return new ResponseEntity<APIResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("##############################################");
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
}
