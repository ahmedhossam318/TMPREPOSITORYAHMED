package mts.ftth.vc4.models;

import java.util.Date;

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
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SYS_AUDIT {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_AUDIT_ID_SEQ")
	@SequenceGenerator(name = "SYS_AUDIT_ID_SEQ",sequenceName = "SYS_AUDIT_ID_SEQ", allocationSize=0)
	private Long ACTION_ID;
	private String ACTION_CODE;
	@JsonFormat(timezone = "GMT+02:00")
	private Date ACTION_TIME;
	private String USERNAME;
	private String IP_ADDRESS;
	private String ACTION_DETAILS;
	private String ACTION_DATA;
	
	@Transient
	private Object JSON;
}
