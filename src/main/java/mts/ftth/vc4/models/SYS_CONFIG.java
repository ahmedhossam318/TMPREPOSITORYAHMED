package mts.ftth.vc4.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="SYS_CONFIG", schema = "vc4_alarm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SYS_CONFIG {
	@Id
	private Long ID;
	private String VC4_USERNAME;
	private String VC4_PASSWORD;
	private String VC4_URL;
	
}
