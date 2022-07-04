package mts.ftth.vc4.models;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class PassiveLineData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8441899963155752007L;
	
	@JsonProperty("RESULT_CODE")
	private String RESULT_CODE;
	@JsonProperty("RESULT_MSG")
	private String RESULT_MSG;
	@JsonProperty("CITY_CODE")
	private String CITY_CODE;
	@JsonProperty("TEL_NO")
	private String TEL_NO;
	@JsonProperty("PHONE_NUMBER")
	private String PHONE_NUMBER;
	@JsonProperty("EXCH_CODE")
	private String EXCH_CODE;
	@JsonProperty("PASSIVE")
	private String PASSIVE;
	@JsonProperty("SPLITTER_ID")
	private String SPLITTER_ID;
	@JsonProperty("SPLITTER_PORT")
	private String SPLITTER_PORT;
	@JsonProperty("Cabinet_ODF_ID")
	private String Cabinet_ODF_ID;
	@JsonProperty("ODF_PORT")
	private String ODF_PORT;
	@JsonProperty("BOX_ID")
	private String BOX_ID;
	@JsonProperty("BOX_TERMINAL")
	private String BOX_TERMINAL;
	@JsonProperty("LONGITUDE")
	private String LONGITUDE;
	@JsonProperty("LATITUDE")
	private String LATITUDE;
}
