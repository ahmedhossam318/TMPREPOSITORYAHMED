package mts.ftth.vc4.models;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


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
	
	public String getEXCH_CODE() {
		return EXCH_CODE;
	}
	public void setEXCH_CODE(String eXCH_CODE) {
		EXCH_CODE = eXCH_CODE;
	}
	public String getCABINET_NO() {
		return CABINET_NO;
	}
	public void setCABINET_NO(String cABINET_NO) {
		CABINET_NO = cABINET_NO;
	}
	public String getSPLITTER_ID() {
		return SPLITTER_ID;
	}
	public void setSPLITTER_ID(String sPLITTER_ID) {
		SPLITTER_ID = sPLITTER_ID;
	}
	public String getGPON_ID() {
		return GPON_ID;
	}
	public void setGPON_ID(String gPON_ID) {
		GPON_ID = gPON_ID;
	}
	public String getGPON_SHELF() {
		return GPON_SHELF;
	}
	public void setGPON_SHELF(String gPON_SHELF) {
		GPON_SHELF = gPON_SHELF;
	}
	public String getGPON_CARD() {
		return GPON_CARD;
	}
	public void setGPON_CARD(String gPON_CARD) {
		GPON_CARD = gPON_CARD;
	}
	public String getGPON_PORT() {
		return GPON_PORT;
	}
	public void setGPON_PORT(String gPON_PORT) {
		GPON_PORT = gPON_PORT;
	}
	public String getMMR_A_ODF() {
		return MMR_A_ODF;
	}
	public void setMMR_A_ODF(String mMR_A_ODF) {
		MMR_A_ODF = mMR_A_ODF;
	}
	public String getMMR_A_PORT() {
		return MMR_A_PORT;
	}
	public void setMMR_A_PORT(String mMR_A_PORT) {
		MMR_A_PORT = mMR_A_PORT;
	}
	public String getMMR_P_ODF() {
		return MMR_P_ODF;
	}
	public void setMMR_P_ODF(String mMR_P_ODF) {
		MMR_P_ODF = mMR_P_ODF;
	}
	public String getMMR_P_PORT() {
		return MMR_P_PORT;
	}
	public void setMMR_P_PORT(String mMR_P_PORT) {
		MMR_P_PORT = mMR_P_PORT;
	}
	public String getSPLITTER_TYPE() {
		return SPLITTER_TYPE;
	}
	public void setSPLITTER_TYPE(String sPLITTER_TYPE) {
		SPLITTER_TYPE = sPLITTER_TYPE;
	}
}
