package mts.ftth.vc4.models;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class UpBox {
	private String EXCH_CODE;
	private String CABINET_NO;
	private String BOX_ID;
	private String BOX_ADDRESS;
	private String BOX_TYPE;
	private String BOX_LONG;
	private String BOX_LAT;
}
