package mts.ftth.vc4.controllers;

import mts.ftth.vc4.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.services.apiInterface.AuditService;
import mts.ftth.vc4.services.apiInterface.CabinetService;
import mts.ftth.vc4.services.apiInterface.LineCardService;

@RestController
@RequestMapping("/api/cabinet")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtsec")
public class CabinetController {
    private static final Logger logger = LogManager.getLogger(GponController.class);

    @Autowired
    CabinetService cabinetService;

    @Autowired
    LineCardService linecardService;

    @Autowired
    VC4Token vc4Token;

    @Autowired
    AuditService audService;

    @GetMapping("/getExchCabinetList")
    public ResponseEntity<APIResponse> getexchCabinetList(@RequestParam(required = false, value = "ExchCode") String exchCode,
                                                          @RequestParam(required = false, value = "projectName") String projectName) {
        String token = "";

        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch getExchCabinetList...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.GetExchCabList(token, exchCode, projectName);
        return res;
    }

    @GetMapping("/getCabSplitterList")
    public ResponseEntity<APIResponse> getCabSplitterList(@RequestParam(value = "cabinetid") String cabId) {
        String token = "";
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch getCabSplitterList...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.GetCabSplitterList(token, cabId);
        return res;
    }

    @GetMapping("/getCabTBoxList")
    public ResponseEntity<APIResponse> getCabTBoxList(@RequestParam(value = "cabinetid") String cabId) {
        String token = "";
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch getCabTBoxList...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.GetCabTBoxList(token, cabId);
        return res;
    }

    @GetMapping("/getSpliierPortList")
    public ResponseEntity<APIResponse> getSpliierPortList(@RequestParam(value = "SplitterId") String splitterId) {
        String token = "";
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch getCabTBoxList...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.GetSplitterPortList(token, splitterId);
        return res;
    }

    @GetMapping("/getCabinetAlarmJobs")
    public ResponseEntity<APIResponse> getCabinetAlarmJobs(@RequestParam(value = "Vc4Id") Long vc4Id) {
        ResponseEntity<APIResponse> res = cabinetService.GetCabinetAlarmJobs(vc4Id);
        return res;
    }


    @GetMapping("/getBoxAlarmJobs")
    public ResponseEntity<APIResponse> getBoxAlarmJobs(@RequestParam(value = "Vc4Id") Long vc4Id) {
        ResponseEntity<APIResponse> res = cabinetService.GetCabinetBoxAlarmJobs(vc4Id);
        return res;
    }


    @PostMapping("/updateFCCSplitter")
    public ResponseEntity<APIResponse> updateSplitter(@RequestBody UpSplitter splitter) {
        String token = "";
        System.out.println("citty : " + splitter.getEXCH_CODE());
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch updateSplitter...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.UpdateSplitter(token, splitter);
        SYS_AUDIT s = new SYS_AUDIT();
        s.setACTION(splitter.getActionName());
        s.setACTION_BY(splitter.getUserName());
        s.setELEMENT_TYPE("SPLITTER");
        s.setELEMENT_VALUE(String.valueOf(splitter.getEXCH_CODE()) + String.valueOf(splitter.getCABINET_NO()) + String.valueOf(splitter.getSPLITTER_ID()));
        audService.audit(s);
        return res;
    }


    @PostMapping("/updateFCCBox")
    public ResponseEntity<APIResponse> updateFCCBox(@RequestBody UpBox box) {
        String token = "";
        APIResponse response = new APIResponse();
        System.out.println("type : " + box.getBOX_ID());
        logger.info("##############################################");
        logger.info("Client request to fetch updateFCCBox...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.UpdateBox(token, box);
        SYS_AUDIT s = new SYS_AUDIT();
        s.setACTION(box.getActionName());
        s.setACTION_BY(box.getUserName());
        s.setELEMENT_TYPE("FCC_BOX");
        s.setELEMENT_VALUE(String.valueOf(box.getEXCH_CODE()) + String.valueOf(box.getCABINET_NO()) + String.valueOf(box.getBOX_ID()));
        audService.audit(s);
        return res;
    }


    @GetMapping("/getFreeSpliierPortList")
    public ResponseEntity<APIResponse> getFreeSpliierPortList(@RequestParam(value = "SplitterId") String splitterId) {
        String token = "";
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch getFreeSpliierPortList...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.GetSplitterFreePortList(token, splitterId);
        return res;
    }

    @PostMapping("/updateFCCBoxSplitter")
    public ResponseEntity<APIResponse> updateFCCBoxSplitter(@RequestBody UpBoxSplitter boxSplitter) {
        String token = "";
//		System.out.println("citty : "+splitter.getEXCH_CODE());
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch updateFCCBoxSplitter...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = cabinetService.UpdateBoxSplitter(token, boxSplitter);
        SYS_AUDIT s = new SYS_AUDIT();
        s.setACTION("UPDATE_BOX_SPLITTER");
        s.setACTION_BY(boxSplitter.getUserName());
        s.setELEMENT_TYPE("BOX_SPLITTER");
        s.setELEMENT_VALUE(String.valueOf(boxSplitter.getSPLITTER_PORT_ID()) + String.valueOf(boxSplitter.getBOX_PORT_ID()));
        audService.audit(s);
        return res;
    }


    @PostMapping("/getBoxTerminals")
    public ResponseEntity<APIResponse> getBoxTerminals(@RequestBody TBPortRequest req) {
        String token = "";
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch getTBPotByPassive...");
        logger.info("##############################################");
        token = vc4Token.token;
        System.out.println("tt :" + token);
        if (token.equals("Fail")) {
            response.setStatus(HttpStatus.REQUEST_TIMEOUT);
            response.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
            return new ResponseEntity<APIResponse>(response, HttpStatus.REQUEST_TIMEOUT);
        }
        ResponseEntity<APIResponse> res = linecardService.getTBPotByPassive(token, req);
        return res;
    }

    @PostMapping("/addCabinet")
    public ResponseEntity<APIResponse> addCabinet(@RequestBody CabinetObj cabinetObj) {
        String token = "";
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch addCabinet...");
        logger.info("##############################################");
        token = vc4Token.token;
        ResponseEntity<APIResponse> res = cabinetService.CreateCabinet(token, cabinetObj);
        SYS_AUDIT s = new SYS_AUDIT();
        s.setACTION("CREATE_CABINET");
//		s.setACTION_BY(cabinetObj.getActionBy());
        s.setELEMENT_TYPE("CABINET");
        s.setELEMENT_VALUE(String.valueOf(cabinetObj.getExchCode()) + String.valueOf(cabinetObj.getCabinetNo()));
        audService.audit(s);
        return res;
    }

    @PostMapping("/updateCabinet")
    public ResponseEntity<APIResponse> updateCabinet(@RequestBody CabinetObj cabinetObj) {
        String token = "";
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch updateCabinet...");
        logger.info("##############################################");
        token = vc4Token.token;
        ResponseEntity<APIResponse> res = cabinetService.CreateCabinet(token, cabinetObj);
        SYS_AUDIT s = new SYS_AUDIT();
        s.setACTION("UPDATE_CABINET");
        s.setELEMENT_TYPE("CABINET");
        s.setELEMENT_VALUE(String.valueOf(cabinetObj.getExchCode()) + String.valueOf(cabinetObj.getCabinetNo()));
        audService.audit(s);
        return res;
    }

    @PostMapping("/deleteCabinet")
    public ResponseEntity<APIResponse> deleteCabinet(@RequestBody CabinetLog cab) {
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch deleteCabinet...");
        logger.info("##############################################");

        ResponseEntity<APIResponse> res = cabinetService.DeleteCabinet(cab);
        SYS_AUDIT s = new SYS_AUDIT();
        s.setACTION("DELETE_CABINET");
        s.setACTION_BY(cab.getActionBy());
        s.setELEMENT_TYPE("CABINET");
        s.setELEMENT_VALUE(String.valueOf(cab.getExchcode()) + String.valueOf(cab.getCabinetno()));
        audService.audit(s);
        return res;
    }

    @PostMapping("/deleteBox")
    public ResponseEntity<APIResponse> deleteBox(@RequestBody CabinetLog cab) {
        APIResponse response = new APIResponse();
        logger.info("##############################################");
        logger.info("Client request to fetch deleteCabinet...");
        logger.info("##############################################");

        ResponseEntity<APIResponse> res = cabinetService.DeleteBox(cab);
        SYS_AUDIT s = new SYS_AUDIT();
        s.setACTION("DELETE_BOX");
        s.setACTION_BY(cab.getActionBy());
        s.setELEMENT_TYPE("BOX");
        s.setELEMENT_VALUE(String.valueOf(cab.getExchcode()) + String.valueOf(cab.getCabinetno()) + String.valueOf(cab.getBoxId()));
        audService.audit(s);
        return res;
    }
}
