package mts.ftth.vc4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="NE_GPON_PORT_ALARM_JOB", schema = "VC4_ALARM")
public class NeGponPortAlarmJob  extends Element  {
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
	
	@Column(name="GPON_ID")
	String jponId;
	 
	@Column(name="SHELF_ID")
	String shelfId;
	
	@Column(name="SLOT_ID")
	String slotId;
	
	@Column(name="PORT_ID")
	String portId;
	
	@Column(name="FAULT_CODE")
	String faultCode;

//	public String getJponId() {
//		return jponId;
//	}
//
//	public void setJponId(String jponId) {
//		this.jponId = jponId;
//	}
//
//	public String getShelfId() {
//		return shelfId;
//	}
//
//	public void setShelfId(String shelfId) {
//		this.shelfId = shelfId;
//	}
//
//	public String getSlotId() {
//		return slotId;
//	}
//
//	public void setSlotId(String slotId) {
//		this.slotId = slotId;
//	}
//
//	public String getPortId() {
//		return portId;
//	}
//
//	public void setPortId(String portId) {
//		this.portId = portId;
//	}

}
