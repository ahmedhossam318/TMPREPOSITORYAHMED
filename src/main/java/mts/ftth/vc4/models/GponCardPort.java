package mts.ftth.vc4.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GponCardPort {
	
    private String id;
	private String nodecardid;
	private String gponnodeid;
	private String cardslot;
	private String portslot;
	private String portstatus;
	private String rowVersion;
	private String errors;
	private String hasErrors;
	private String sfpclass ;
	private String sfptype ;

}
