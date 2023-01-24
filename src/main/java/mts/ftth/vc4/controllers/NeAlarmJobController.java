package mts.ftth.vc4.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.models.Element;
import mts.ftth.vc4.models.ElementObject;
import mts.ftth.vc4.models.FTTHNeOutage;
import mts.ftth.vc4.models.NeBoxAlarmJob;
import mts.ftth.vc4.models.NeCabinetAlarmJob;
import mts.ftth.vc4.models.NeGponAlarmJob;
import mts.ftth.vc4.models.NeGponCardAlarmJob;
import mts.ftth.vc4.models.NeGponPortAlarmJob;
import mts.ftth.vc4.models.SYS_AUDIT;
import mts.ftth.vc4.models.FinishObject;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.apiInterface.AuditService;
import mts.ftth.vc4.services.NeAlarmJobServiceImpl;

@RestController
@RequestMapping("/api/alarm")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class NeAlarmJobController {
    private static final Logger logger = LogManager.getLogger(NeAlarmJobController.class);


    @Autowired
    NeAlarmJobServiceImpl jobService;

    @Autowired
    AuditService audService;


    @GetMapping("/getJobList")
    public ResponseEntity<APIResponse> getJobList(@RequestParam(value = "VC4Id") String vc4Id) {

        return null;
    }

    @GetMapping("/getJobStatus")
    public ResponseEntity<APIResponse> getJobStatus(@RequestParam(value = "VC4Id") long vc4Id) {

        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch job status...");
        logger.info("##############################################");


        response.setStatus(HttpStatus.OK);
        response.setJobStatus(jobService.queryJob(vc4Id));
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/finishJob")
    public ResponseEntity<APIResponse> finishJob(@RequestBody FinishObject finish) {

        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to finish job ...");
        logger.info("##############################################");

        String finishSts = jobService.finishJob(finish.getNeType(), finish.getVc4Id(), finish.getFaultReason(), finish.getFinishUser(), finish.getActualRepairDate(), finish.getNotes(), finish.getFaultCode());
        if (finishSts.equals("SUCCESS")) {
            SYS_AUDIT s = new SYS_AUDIT();
            s.setACTION("FINISH_JOB");
            s.setACTION_BY(finish.getFinishUser());
            s.setELEMENT_TYPE(finish.getNeType());
            s.setELEMENT_VALUE(String.valueOf(finish.getVc4Id()));
            audService.audit(s);

            response.setStatus(HttpStatus.OK);
            response.setFinishStatus("Success");
            return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
        } else if (finishSts.equals("not_valid_repaire")) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setFinishStatus("Not Valid Repair Date");
            return new ResponseEntity<APIResponse>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setFinishStatus("Fail");
            return new ResponseEntity<APIResponse>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getElementFaulty")
    public ResponseEntity<APIResponse> getElementFaulty(@RequestParam(value = "elementType") String neType) {

        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch element faulty...");
        logger.info("##############################################");

        List<FTTHNeOutage> elementJobs = jobService.getElementJobs(neType);
        response.setStatus(HttpStatus.OK);
        response.setElementJobs(elementJobs);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/updateElementJob")
    public ResponseEntity<APIResponse> updateElementJob(@RequestBody NeGponAlarmJob neGponAlarmJob) {
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to update job ...");
        logger.info("##############################################");
        try {
            String createSts = jobService.updateElementJobs(neGponAlarmJob);
            if (createSts.equals("success")) {
                SYS_AUDIT s = new SYS_AUDIT();
                s.setACTION("UPDATE_JOB");
                s.setACTION_BY(neGponAlarmJob.getUserName());
//				s.setELEMENT_TYPE(neGponAlarmJob.);
//				s.setELEMENT_VALUE(String.valueOf(e.getVc4Id()));
                audService.audit(s);
            }
            response.setStatus(HttpStatus.OK);
            response.setCreateStatus(createSts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/createElementJob")
    public ResponseEntity<APIResponse> createElementJob(@RequestParam(value = "elementType") String neType
            , @RequestBody ElementObject element) {
//		throws JsonMappingException, JsonProcessingException{
//	}
//			@RequestParam(value = "vc4id") Long vc4id,@RequestParam(value = "promiseDate") String promiseDate,
//			@RequestParam(value = "faultyReason") String faultyReason,
//			@RequestParam(value = "notes") String notes){
        //System.out.println("element VC4ID:"+element.getVc4Id());
//       System.out.println("getVc4Id::"+element.getGponElement().getVc4Id());

//		ObjectMapper mapper = new ObjectMapper();
//		NeGponAlarmJob result= mapper.readValue(element, NeGponAlarmJob.class);

        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to create job ...");
        logger.info("##############################################");
        Element e = null;
//		e.setVc4Id((long) 1);
//		return new ResponseEntity<NeGponAlarmJob>((NeGponAlarmJob)element, HttpStatus.OK);
        if (neType.equalsIgnoreCase("GPON")) {
            System.out.println("GPON###########################################:");
//			NeGponAlarmJob j = (NeGponAlarmJob) element;
//			System.out.println("element VC4ID:"+j.getVc4Id());
            e = (NeGponAlarmJob) element.getGponElement();

        } else if (neType.equalsIgnoreCase("CARD")) {
            e = (NeGponCardAlarmJob) element.getCardElement();
        } else if (neType.equalsIgnoreCase("PORT")) {
            e = (NeGponPortAlarmJob) element.getPortElement();

        } else if (neType.equalsIgnoreCase("CABINET")) {
            e = (NeCabinetAlarmJob) element.getCabinetElement();

        } else if (neType.equalsIgnoreCase("BOX")) {
            e = (NeBoxAlarmJob) element.getBoxElement();

        }

        System.out.println("element VC4ID:" + e.getVc4Id());
        String createSts = jobService.createJob(neType, e);
        if (createSts.equals("success")) {
            SYS_AUDIT s = new SYS_AUDIT();
            s.setACTION("CREATE_JOB");
            s.setACTION_BY(e.getUserName());
            s.setELEMENT_TYPE(neType);
            s.setELEMENT_VALUE(String.valueOf(e.getVc4Id()));
            audService.audit(s);
        }
        response.setStatus(HttpStatus.OK);
        response.setCreateStatus(createSts);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }

}
