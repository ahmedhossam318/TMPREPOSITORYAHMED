package mts.ftth.vc4.models;

public class GponCard {
    private String id;
	private String gponid;
	private String msancode;
	private String gponname;
	private String gponnodeid;
	private String cardclass;
	private String cardname;
	private String cardslot;
	private String rowVersion;
	private String errors;
	private String hasErrors;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGponid() {
		return gponid;
	}
	public void setGponid(String gponid) {
		this.gponid = gponid;
	}
	public String getMsancode() {
		return msancode;
	}
	public void setMsancode(String msancode) {
		this.msancode = msancode;
	}
	public String getGponname() {
		return gponname;
	}
	public void setGponname(String gponname) {
		this.gponname = gponname;
	}
	public String getGponnodeid() {
		return gponnodeid;
	}
	public void setGponnodeid(String gponnodeid) {
		this.gponnodeid = gponnodeid;
	}
	public String getCardclass() {
		return cardclass;
	}
	public void setCardclass(String cardclass) {
		this.cardclass = cardclass;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardslot() {
		return cardslot;
	}
	public void setCardslot(String cardslot) {
		this.cardslot = cardslot;
	}
	public String getRowVersion() {
		return rowVersion;
	}
	public void setRowVersion(String rowVersion) {
		this.rowVersion = rowVersion;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(String hasErrors) {
		this.hasErrors = hasErrors;
	}
}
