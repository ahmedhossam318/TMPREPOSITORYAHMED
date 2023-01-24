package mts.ftth.vc4.services.apiInterface;


import mts.ftth.vc4.models.Element;
import mts.ftth.vc4.models.FTTHNeOutage;

import java.util.List;

import mts.ftth.vc4.models.NeGponAlarmJob;
import org.springframework.http.ResponseEntity;

import mts.ftth.vc4.payload.response.APIResponse;


public interface NeAlarmJobService {

//	public List<NeAlarmJob> getAllJob(String neType,String vc4Id);

    public long queryJob(long vc4Id);

    public String createJob(String neType, Element element);

    public String finishJob(String neType, Long vc4Id, String faultyReason, String finishUser
            , String actualClosureDate, String notes, String faultCode);

    public List<FTTHNeOutage> getElementJobs(String type);

    public String updateElementJobs(NeGponAlarmJob neGponAlarmJob);
}
