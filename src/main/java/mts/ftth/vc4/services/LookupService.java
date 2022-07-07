package mts.ftth.vc4.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.models.Fault;
import mts.ftth.vc4.payload.response.APIResponse;


public interface LookupService {
	
	public List<Fault>GetAllFault();
	
	public ResponseEntity<APIResponse> GetTBoxType(String vc4Tocken);
	
}
