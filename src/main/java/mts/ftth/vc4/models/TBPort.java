package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TBPort {
	@JsonProperty("PORT_ID") 
    public int pORT_ID;
    @JsonProperty("Cabinet_No") 
    public String cabinet_No;
    @JsonProperty("Box") 
    public String box;
    @JsonProperty("PORT_NAME") 
    public String pORT_NAME;
    @JsonProperty("TERMINAL_ID") 
    public String tERMINAL_ID;
    @JsonProperty("SPLITTER_ID") 
    public String sPLITTER_ID;
    @JsonProperty("SPLITTER_PORT") 
    public String sPLITTER_PORT;
    @JsonProperty("ODF_ID") 
    public String oDF_ID;
    @JsonProperty("ODF_PORT") 
    public String oDF_PORT;
    @JsonProperty("CABLE_ID") 
    public String cABLE_ID;
    @JsonProperty("LineCard") 
    public String lineCard;
}
