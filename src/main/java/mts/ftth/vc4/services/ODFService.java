package mts.ftth.vc4.services;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.payload.response.APIResponse;

public interface ODFService {
	public ResponseEntity<APIResponse> GetODFList(String vc4Tocken);
	
	public ResponseEntity<APIResponse> GetODFPortList(String vc4Tocken,String odfId);
	
	public ResponseEntity<APIResponse> GetODFListByType(String vc4Tocken,String type);
}
