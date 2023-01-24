package mts.ftth.vc4.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="NW_FAULT_REASON", schema = "VC4_ALARM")
@Setter
@Getter
public class Fault {
	@Id
	@Column(name="FAULT_CODE")
    private String faultCode;
	@Column(name="FAULT_NAME")
    private String faultReason;

}
