package mts.ftth.vc4.services;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.models.UpBox;
import mts.ftth.vc4.models.UpSplitter;
import mts.ftth.vc4.payload.response.APIResponse;

public interface CabinetService {
	public ResponseEntity<APIResponse> GetExchCabList(String vc4Tocken,String exchCode);
	public ResponseEntity<APIResponse> GetCabSplitterList(String vc4Tocken,String cabId);
	public ResponseEntity<APIResponse> GetCabTBoxList(String vc4Tocken,String cabId);
	public ResponseEntity<APIResponse> GetSplitterPortList(String vc4Tocken,String splitterId);
	
	public ResponseEntity<APIResponse> GetCabinetAlarmJobs(Long vc4Id);
	public ResponseEntity<APIResponse> GetCabinetBoxAlarmJobs(Long vc4Id);
	public ResponseEntity<APIResponse> GetSplitterTypes(String vc4Tocken);
	
	public ResponseEntity<APIResponse> UpdateSplitter(String vc4Tocken,UpSplitter splitter);
	
	public ResponseEntity<APIResponse> UpdateBox(String vc4Tocken,UpBox box);
}
