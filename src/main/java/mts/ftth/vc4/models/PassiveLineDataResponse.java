package mts.ftth.vc4.models;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class PassiveLineDataResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7103732249124916070L;
	
	
	private String message;
	private String action;
	private ActiveLineData parameters;

}
