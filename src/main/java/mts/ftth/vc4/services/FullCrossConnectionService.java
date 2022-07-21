package mts.ftth.vc4.services;

import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.payload.response.APIResponse;

public interface FullCrossConnectionService {
	public ResponseEntity<APIResponse> getFullCrossConnection(String vc4Tocken,String exchCode,int paginatorStartElement, int paginatorNumberOfElements);
}
