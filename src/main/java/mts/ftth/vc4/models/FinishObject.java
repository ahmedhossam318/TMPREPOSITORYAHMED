package mts.ftth.vc4.models;


public class FinishObject {
	private String neType;
	private long vc4Id;
	private String faultReason;
	private String finishUser;
	
	private String actualRepairDate;
	private String notes;
	private String faultCode;
	public String getNeType() {
		return neType;
	}
	public void setNeType(String neType) {
		this.neType = neType;
	}
	public long getVc4Id() {
		return vc4Id;
	}
	public void setVc4Id(long vc4Id) {
		this.vc4Id = vc4Id;
	}
	public String getFaultReason() {
		return faultReason;
	}
	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}
	public String getFinishUser() {
		return finishUser;
	}
	public void setFinishUser(String finishUser) {
		this.finishUser = finishUser;
	}
	public String getActualRepairDate() {
		return actualRepairDate;
	}
	public void setActualRepairDate(String actualRepairDate) {
		this.actualRepairDate = actualRepairDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getFaultCode() {
		return faultCode;
	}
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

}
