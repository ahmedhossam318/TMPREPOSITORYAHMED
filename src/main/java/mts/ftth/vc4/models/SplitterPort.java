package mts.ftth.vc4.models;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Configuration
@RequiredArgsConstructor
public class SplitterPort  implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("PORT_ID")
	private String PORT_ID;
	@JsonProperty("PORT_NAME")
	private String PORT_NAME;
	@JsonProperty("PORT_STATUS")
	private String PORT_STATUS;
}
