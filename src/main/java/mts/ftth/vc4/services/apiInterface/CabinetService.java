package mts.ftth.vc4.services.apiInterface;

import mts.ftth.vc4.models.*;
import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.payload.response.APIResponse;

public interface CabinetService {
    public ResponseEntity<APIResponse> GetExchCabList(String vc4Tocken, String exchCode, String projectName);

    public ResponseEntity<APIResponse> GetCabSplitterList(String vc4Tocken, String cabId);

    public ResponseEntity<APIResponse> GetCabTBoxList(String vc4Tocken, String cabId);

    public ResponseEntity<APIResponse> GetSplitterPortList(String vc4Tocken, String splitterId);

    public ResponseEntity<APIResponse> GetCabinetAlarmJobs(Long vc4Id);

    public ResponseEntity<APIResponse> GetCabinetBoxAlarmJobs(Long vc4Id);

    public ResponseEntity<APIResponse> GetSplitterTypes(String vc4Tocken);

    public ResponseEntity<APIResponse> UpdateSplitter(String vc4Tocken, UpSplitter splitter);

    public ResponseEntity<APIResponse> UpdateBox(String vc4Tocken, UpBox box);

    public ResponseEntity<APIResponse> GetSplitterFreePortList(String vc4Tocken, String splitterId);

    public ResponseEntity<APIResponse> UpdateBoxSplitter(String vc4Tocken, UpBoxSplitter boxSplitter);

    public ResponseEntity<APIResponse> CreateCabinet(String token, CabinetObj cabinetObj);

    public ResponseEntity<APIResponse> DeleteCabinet(CabinetLog cabinet);

    public ResponseEntity<APIResponse> DeleteBox(CabinetLog cabinet);
}
