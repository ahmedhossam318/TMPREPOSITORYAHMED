package mts.ftth.vc4.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="NE_GPON_ALARM_JOB", schema = "VC4_ALARM")
@RequiredArgsConstructor
public class NeGponAlarmJob  extends Element  {


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
	@JsonFormat(timezone = "GMT+02:00")
	Date instanceClosure;
	
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
	
	@Column(name="GPON_ID")
	String jponId;
	 
	@Column(name="SHELF_ID")
	String shelfId;
	
	@Column(name="FAULT_CODE")
	String faultCode;


}
