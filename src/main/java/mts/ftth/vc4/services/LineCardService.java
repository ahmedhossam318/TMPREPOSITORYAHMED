package mts.ftth.vc4.services;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.payload.response.APIResponse;

public interface LineCardService {
	public ResponseEntity<APIResponse> getActiveDataLineCard(String vc4Tocken,String cityCode,String telNo);
	
	public ResponseEntity<APIResponse> getPassiveDataLineCard(String vc4Tocken,String cityCode,String telNo);
	

}
