package mts.ftth.vc4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FTTH_NE_OUTAGE", schema = "VC4_ALARM")
public class FTTHNeOutage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "vc4_alarm.FTTH_NE_OUTAGE_SEQ", allocationSize=1)
	@Column(name="NE_ID")
	private Long neId;
	
	@Column(name="VC4_ID")
	private Long vc4Id;
	
	@Column(name="NE_TYPE")
	private String neType;
	
	@Column(name="FAULTY")
	private Long faulty;
	
	public Long getNeId() {
		return neId;
	}
	public void setNeId(Long neId) {
		this.neId = neId;
	}
	public Long getVc4Id() {
		return vc4Id;
	}
	public void setVc4Id(Long vc4Id) {
		this.vc4Id = vc4Id;
	}
	public String getNeType() {
		return neType;
	}
	public void setNeType(String neType) {
		this.neType = neType;
	}
	public Long getFaulty() {
		return faulty;
	}
	public void setFaulty(Long faulty) {
		this.faulty = faulty;
	}

}
