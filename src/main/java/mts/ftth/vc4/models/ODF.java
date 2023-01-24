package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ODF {
    private String id;
	private String capacity;
	private String odfcat;
	private String odftype;
	private String odfname;
	private String rowVersion;
	private String errors;
	private String hasErrors;

	@JsonProperty("statusname")
	private String statusName;
	private String poplist;
	private String mmrodf;
	private String exchcode;
}
