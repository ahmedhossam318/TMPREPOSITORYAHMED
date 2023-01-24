package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ODFPort {
    private String id;
	private String odfcat;
	private String odftype;
	private String cableid;
	private String frontback;
	private String portname;
	private String portid;
	private String odfname;
	private String odfid;
	private String port;
	private String rowVersion;
	private String errors;
	private String hasErrors;
	private String portstatus;
	private String cabinetno;
	private String splitter;
	private String exchcode;
	private String portpos;
	private String attenuation;


}
