package mts.ftth.vc4.globalControllers;

//import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.payload.request.LoginRequest;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.apiInterface.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class AuthController {

	private final AuthService authService;
	private static final Logger logger = LogManager.getLogger(AuthController.class);
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		logger.info("Login Request for user: {} wit IP: {}", loginRequest.getUsername());
		if(loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
			
			APIResponse response = new APIResponse();
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setClientMessage("Missing username or password");
			return new ResponseEntity<APIResponse>(response, HttpStatus.BAD_REQUEST);
			
		}
			
		return ResponseEntity.ok(authService.authenticateUser(loginRequest));
	}
	
}
