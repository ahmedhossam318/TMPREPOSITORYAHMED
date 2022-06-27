package mts.ftth.vc4.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Prameters implements Serializable{
	
@JsonProperty("Ports")
private List<SplitterPort> ports;
}
