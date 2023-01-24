package mts.ftth.vc4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value= {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		
		ApiException apiException = new ApiException(
			e.getMessage(),
			HttpStatus.BAD_REQUEST,
			ZonedDateTime.now(ZoneId.of("Z"))
		);
		
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
	}
	
}
