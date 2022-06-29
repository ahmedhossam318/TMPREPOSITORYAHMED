package mts.ftth.vc4.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.models.Gpon;
import mts.ftth.vc4.payload.response.APIResponse;

public interface GponService {
	public ResponseEntity<APIResponse> GetAllGpon(String vc4Tocken,int PaginatorStartElement , int PaginatorNumberOfElements,String exchCode);
	
	public ResponseEntity<APIResponse> GetGponCards(String vc4Tocken,String gponId);
	
	public ResponseEntity<APIResponse> GetGponCardPorts(String vc4Tocken,String cardId);
	
	public ResponseEntity<APIResponse> GetGponAlarmJobs(Long vc4Id);
	
	public ResponseEntity<APIResponse> GetGponCardAlarmJobs(Long vc4Id);
	
	public ResponseEntity<APIResponse> GetGponPortAlarmJobs(Long vc4Id);
	
}
