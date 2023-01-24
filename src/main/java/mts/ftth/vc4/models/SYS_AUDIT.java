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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name="ELEMENT_AUDIT", schema = "VC4_ALARM")
public class SYS_AUDIT implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_AUDIT_ID_SEQ")
	@SequenceGenerator(name = "SYS_AUDIT_ID_SEQ",sequenceName = "vc4_alarm.SYS_AUDIT_ID_SEQ", allocationSize=0)
	private Long ACTION_ID;
	@Column
	private String ELEMENT_TYPE;
	@Column(insertable = false)
	@JsonFormat(timezone = "GMT+02:00")
	private Date ACTION_DATE;
	@Column
	private String ACTION_BY;
	@Column
	private String ACTION;
	@Column
	private String ELEMENT_VALUE;
	
}
