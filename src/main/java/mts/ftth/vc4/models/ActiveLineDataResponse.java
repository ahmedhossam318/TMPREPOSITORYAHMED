package mts.ftth.vc4.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class ActiveLineDataResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String action;
	private ActiveLineData parameters;

}
