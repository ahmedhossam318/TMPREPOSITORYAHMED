package mts.ftth.vc4.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GponCard {
    private String id;
	private String gponid;
	private String msancode;
	private String gponname;	
	private String gponnodeid;
	private String cardclass;
	private String cardname;
	private String slot;
	private String rowVersion;
	private String errors;
	private String hasErrors;
	private String cardStatusName;
	private String subscribers;
	private String totalports;


}
