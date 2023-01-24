package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrametersUpBoxSplitter {
	@JsonProperty("Result_Code")
	private String resCode;
	@JsonProperty("Result_Message")
    private String resMessage;
	@JsonProperty("ROWVERSION")
    private String rowVersion;
    
}
