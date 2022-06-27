package mts.ftth.vc4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="NE_CABINET_ALARM_JOB", schema = "VC4_ALARM")
public class NeCabinetAlarmJob  extends Element  {
	
	@Id
	@Column(name="JOB_NO")
	Long jobNo;
	
	@Column(name="VC4_ID")
	Long vc4Id;
	
	@Column(name="PROMISED_REPAIR_DATE")
	String promisedRepairDate;
	
	@Column(name="ACTUAL_REPAIR_DATE")
	String actualRepairDate;
	
	@Column(name="INSTANT_CLOSURE")
	String instanceClosure;
	
	@Column(name="USER_NAME")
	String userName;
	
	@Column(name="JOB_FLAG")
	Long jobFlag;
	
	@Column(name="OUT_OF_SERVICE")
	String outOfService;
	
	@Column(name="JOB_DATE")
	String jobDate;
	
	@Column(name="FINISH_USER")
	String finishUser;
	
	@Column(name="NOTES")
	String notes;
	
	@Column(name="FAULT_REASON")
	String faultReason;
	
	@Column(name="EXCH_CODE")
	String exchCode;
	
	@Column(name="CABINET_NO")
	String cabinetNo;
	
	@Column(name="FAULT_CODE")
	String faultCode;
	
//	public String getExchCode() {
//		return exchCode;
//	}
//
//	public void setExchCode(String exchCode) {
//		this.exchCode = exchCode;
//	}
//
//	public String getCabinetNo() {
//		return cabinetNo;
//	}
//
//	public void setCabinetNo(String cabinetNo) {
//		this.cabinetNo = cabinetNo;
//	}

	

}
