package mts.ftth.vc4.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TBParameters {
	@JsonProperty("Ports") 
    public ArrayList<TBPort> ports;
}
