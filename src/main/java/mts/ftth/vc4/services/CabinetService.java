package mts.ftth.vc4.services;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.payload.response.APIResponse;

public interface CabinetService {
	public ResponseEntity<APIResponse> GetExchCabList(String vc4Tocken,String exchCode);
	public ResponseEntity<APIResponse> GetCabSplitterList(String vc4Tocken,String cabId);
	public ResponseEntity<APIResponse> GetCabTBoxList(String vc4Tocken,String cabId);
}
