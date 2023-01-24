package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Gpon {
	private String id;
	private String gponid;
	private String shelfid;
	private String vendor;
	private String msanip;
	private String msancode;
	private String latitude;
	private String longitude;
	private String gponmodel;
	private String installationlocation;
	private String popname;
	private String popid;
	private String exchangename;
	private String exchange;
	private String gponname;
	private String rowVersion;
	private String hasErrors;
	private String errors;
	
	
	/// 
	private String status;
	private String locationtype;
	@JsonProperty("switch")
	private String switchProp;
	private  String statusname;
	private  String exchcode;
}
