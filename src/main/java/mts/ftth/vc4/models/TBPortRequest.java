package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TBPortRequest {
	@JsonProperty("PASSIVE_ID") 
    public int pASSIVE_ID;
}
