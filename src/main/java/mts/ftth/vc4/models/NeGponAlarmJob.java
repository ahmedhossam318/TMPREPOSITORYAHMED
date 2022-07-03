package mts.ftth.vc4.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity

@Table(name="NE_GPON_ALARM_JOB", schema = "VC4_ALARM")
@RequiredArgsConstructor
public class NeGponAlarmJob  extends Element  {
	
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

	public String getPromisedRepairDate() {
		return promisedRepairDate;
	}

	public void setPromisedRepairDate(String promisedRepairDate) {
		this.promisedRepairDate = promisedRepairDate;
	}

	public String getActualRepairDate() {
		return actualRepairDate;
	}

	public void setActualRepairDate(String actualRepairDate) {
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

	public String getJponId() {
		return jponId;
	}

	public void setJponId(String jponId) {
		this.jponId = jponId;
	}

	public String getShelfId() {
		return shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
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
	String promisedRepairDate;
	
	@Column(name="ACTUAL_REPAIR_DATE")
	String actualRepairDate;
	
	@Column(name="INSTANT_CLOSURE")
	String instanceClosure;
	
	@Column(name="USER_NAME")
	String userName;
	
	@Column(name="JOB_FLAG")
	Long jobFlag=(long) 1;
	
	@Column(name="OUT_OF_SERVICE")
	Date outOfService;
	
	@Column(name="JOB_DATE")
	String jobDate;
	
	@Column(name="FINISH_USER")
	String finishUser;
	
	@Column(name="NOTES")
	String notes;
	
	@Column(name="FAULT_REASON")
	String faultReason;
	
	@Column(name="GPON_ID")
	String jponId;
	 
	@Column(name="SHELF_ID")
	String shelfId;
	
	@Column(name="FAULT_CODE")
	String faultCode;


}
