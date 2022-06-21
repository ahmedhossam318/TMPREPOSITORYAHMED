package mts.ftth.vc4.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditSearch {
	
	private String USERNAME;
	private String ACTION_DATA;
	private String FACTION_DATE;
	private String SACTION_DATE;
}
