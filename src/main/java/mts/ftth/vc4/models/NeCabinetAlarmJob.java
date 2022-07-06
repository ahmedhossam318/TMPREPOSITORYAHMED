package mts.ftth.vc4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import lombok.Data;

@Entity

@Table(name="NE_CABINET_ALARM_JOB", schema = "VC4_ALARM")
public class NeCabinetAlarmJob  extends Element  {
	
	public Long getJobNo() {
		return jobNo;
	}

	public void setJobNo(Long jobNo) {
		this.jobNo = jobNo;
	}

	public Long getVc4Id() {
		return vc4Id;
	}

	public void setVc4Id(Long vc4Id) {
		this.vc4Id = vc4Id;
	}

	public Date getPromisedRepairDate() {
		return promisedRepairDate;
	}

	public void setPromisedRepairDate(Date promisedRepairDate) {
		this.promisedRepairDate = promisedRepairDate;
	}

	public Date getActualRepairDate() {
		return actualRepairDate;
	}

	public void setActualRepairDate(Date actualRepairDate) {
		this.actualRepairDate = actualRepairDate;
	}

	public String getInstanceClosure() {
		return instanceClosure;
	}

	public void setInstanceClosure(String instanceClosure) {
		this.instanceClosure = instanceClosure;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getJobFlag() {
		return jobFlag;
	}

	public void setJobFlag(Long jobFlag) {
		this.jobFlag = jobFlag;
	}

	public Date getOutOfService() {
		return outOfService;
	}

	public void setOutOfService(Date outOfService) {
		this.outOfService = outOfService;
	}

	public String getJobDate() {
		return jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	public String getFinishUser() {
		return finishUser;
	}

	public void setFinishUser(String finishUser) {
		this.finishUser = finishUser;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFaultReason() {
		return faultReason;
	}

	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}

	public String getExchCode() {
		return exchCode;
	}

	public void setExchCode(String exchCode) {
		this.exchCode = exchCode;
	}

	public String getCabinetNo() {
		return cabinetNo;
	}

	public void setCabinetNo(String cabinetNo) {
		this.cabinetNo = cabinetNo;
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "vc4_alarm.JOB_ID_SEQ", allocationSize=1)
	@Column(name="JOB_NO")
	Long jobNo;
	
	@Column(name="VC4_ID")
	Long vc4Id;
	
	@Column(name="PROMISED_REPAIR_DATE")
	@JsonFormat(timezone = "GMT+02:00")
	Date promisedRepairDate;
	
	@Column(name="ACTUAL_REPAIR_DATE")
	@JsonFormat(timezone = "GMT+02:00")
	Date actualRepairDate;
	
	@Column(name="INSTANT_CLOSURE")
	String instanceClosure;
	
	@Column(name="USER_NAME")
	String userName;
	
	@Column(name="JOB_FLAG")
	Long jobFlag=(long) 1;
	
	@Column(name="OUT_OF_SERVICE",insertable = false)
	@JsonFormat(timezone = "GMT+02:00")
	Date outOfService;
	
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
