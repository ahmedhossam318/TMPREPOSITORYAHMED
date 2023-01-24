package mts.ftth.vc4.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="CABINET_LOG", schema = "VC4_ALARM")
public class CabinetLog implements Serializable {
	@Id
	@Column(name="LOG_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAB_LOG_SEQ")
	@SequenceGenerator(name = "CAB_LOG_SEQ",sequenceName = "vc4_alarm.CAB_LOG_SEQ", allocationSize=0)
	private Long logId;

	@Column(name="ID")
	private String id;
	
	@Column(name="EXCH_CODE")
	private String exchcode;
	
	@Column(name="EXCH_NAME")
	private String exchname;
	
	@Column(name="SITE_NAME")
	private String sitename;
	
	@Column(name="MSAN_CODE")
	private String msancode;
	
	@Column(name="CABINET_NO")
	private String cabinetno;
	
	@Column(name="CABINET_NAME")
	private String cabinetname;
	
	@Column(name="LONGITUDE")
	private String longitude;
	
	@Column(name="LATITUDE")
	private String latitude;
	
	@Column(name="POP_ID")
	private String popid;
	
	@Column(name="POP_NAME")
	private String popname;
	
	@Column(name="ROW_VERSION")
	private String rowVersion;
	
	@Column(name="HAS_ERRORS")
	private String hasErrors;
	
	@Column(name="ERRORS")
	private String errors;
	
	@Column(name="ACTION_BY")
	private String actionBy;
	
	@Column(name="ACTION_DATE",insertable = false)
	@JsonFormat(timezone = "GMT+02:00")
	private Date actionDate;
	
	@Column(name="ACTION_NAME")
	private String actionName;
	
	@Column(name="BOX_ID")
	private String boxId;
}
