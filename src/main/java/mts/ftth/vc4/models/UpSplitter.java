package mts.ftth.vc4.models;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class UpSplitter {
	private String EXCH_CODE;
	private String CABINET_NO;
	private String SPLITTER_ID;
	private String GPON_ID;
	private String GPON_SHELF;
	private String GPON_CARD;
	private String GPON_PORT;
	private String MMR_A_ODF;
	private String MMR_A_PORT;
	private String MMR_P_ODF;
	private String MMR_P_PORT;
	private String SPLITTER_TYPE;
}
