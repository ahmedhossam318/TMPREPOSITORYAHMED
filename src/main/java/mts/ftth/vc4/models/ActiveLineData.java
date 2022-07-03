package mts.ftth.vc4.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveLineData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("RESULT_CODE")
	private String RESULT_CODE;
	@JsonProperty("RESULT_MESSAGE")
	private String RESULT_MESSAGE;
	@JsonProperty("CITY_CODE")
	private String CITY_CODE;
	@JsonProperty("TEL_NO")
	private String TEL_NO;
	@JsonProperty("PHONE_NUMBER")
	private String PHONE_NUMBER;
	@JsonProperty("SECONDARY_TEL")
	private String SECONDARY_TEL;
	@JsonProperty("EXCH_CODE")
	private String EXCH_CODE;
	@JsonProperty("SWITCH")
	private String SWITCH;
	@JsonProperty("GPON_ID")
	private String GPON_ID;
	@JsonProperty("SHELF_ID")
	private String SHELF_ID;
	@JsonProperty("SLOT_ID")
	private String SLOT_ID;
	@JsonProperty("PORT_ID")
	private String PORT_ID;
	@JsonProperty("ONT_TYPE")
	private String ONT_TYPE;
	@JsonProperty("ONT_SERIAL")
	private String ONT_SERIAL;
	@JsonProperty("VOICE_VLAN")
	private String VOICE_VLAN;
	@JsonProperty("VOICE_IP")
	private String VOICE_IP;
	@JsonProperty("DOMAIN_NAME")
	private String DOMAIN_NAME;
	@JsonProperty("GPON_CODE")
	private String GPON_CODE;
}
