package mts.ftth.vc4.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditSearch {
	@JsonProperty("USERNAME")
	private String USERNAME;
	@JsonProperty("ACTION_DATA")
	private String ACTION_DATA;
	@JsonProperty("FACTION_DATE")
	private String FACTION_DATE;
	@JsonProperty("SACTION_DATE")
	private String SACTION_DATE;
}
