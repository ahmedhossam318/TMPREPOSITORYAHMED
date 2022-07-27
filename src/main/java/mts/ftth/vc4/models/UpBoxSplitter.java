package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpBoxSplitter {
	   
	@JsonProperty("BOX_PORT_ID") 
	public int bOX_PORT_ID;
	@JsonProperty("SPLITTER_PORT_ID") 
	public int sPLITTER_PORT_ID;
//	@JsonProperty("ODF_ID") 
//	public String oDF_ID;
//	@JsonProperty("ODF_PORT") 
//	public String oDF_PORT;
}
