package mts.ftth.vc4.models;

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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodecardid() {
		return nodecardid;
	}
	public void setNodecardid(String nodecardid) {
		this.nodecardid = nodecardid;
	}
	public String getGponnodeid() {
		return gponnodeid;
	}
	public void setGponnodeid(String gponnodeid) {
		this.gponnodeid = gponnodeid;
	}
	public String getCardslot() {
		return cardslot;
	}
	public void setCardslot(String cardslot) {
		this.cardslot = cardslot;
	}
	public String getPortslot() {
		return portslot;
	}
	public void setPortslot(String portslot) {
		this.portslot = portslot;
	}
	public String getPortstatus() {
		return portstatus;
	}
	public void setPortstatus(String portstatus) {
		this.portstatus = portstatus;
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
