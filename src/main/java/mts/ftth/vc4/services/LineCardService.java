package mts.ftth.vc4.services;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.models.TBPortRequest;
import mts.ftth.vc4.models.UpLineCardRequest;
import mts.ftth.vc4.payload.response.APIResponse;

public interface LineCardService {
	public ResponseEntity<APIResponse> getActiveDataLineCard(String vc4Tocken,String cityCode,String telNo);
	
	public ResponseEntity<APIResponse> getPassiveDataLineCard(String vc4Tocken,String cityCode,String telNo);
	
	public ResponseEntity<APIResponse> getTBByGponCardPort(String vc4Tocken,String gponId,String cardId,String portNo);
	
	public ResponseEntity<APIResponse> getTBPotByPassive(String vc4Tocken,TBPortRequest req);
	
	public ResponseEntity<APIResponse> updateFccLineCard(String vc4Tocken,UpLineCardRequest req);
}
