package mts.ftth.vc4.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mts.ftth.vc4.controllers.VC4Token;
import mts.ftth.vc4.models.ActiveLineDataResponse;
import mts.ftth.vc4.models.Cabinet;
import mts.ftth.vc4.models.GponCard;
import mts.ftth.vc4.models.NeBoxAlarmJob;
import mts.ftth.vc4.models.NeCabinetAlarmJob;
import mts.ftth.vc4.models.NeGponPortAlarmJob;
import mts.ftth.vc4.models.Splitter;
import mts.ftth.vc4.models.SplitterPort;
import mts.ftth.vc4.models.SplitterPortResponse;
import mts.ftth.vc4.models.SplitterType;
import mts.ftth.vc4.models.TBox;
import mts.ftth.vc4.models.UpSplitter;
import mts.ftth.vc4.models.UpSplitterResponse;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.repos.NeBoxAlarmJobRepository;
import mts.ftth.vc4.repos.NeCabinetAlarmJobRepository;
import mts.ftth.vc4.repos.NeGponAlarmJobRepository;
import mts.ftth.vc4.security.SSLTool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class CabinetServiceImpl implements CabinetService{
	
	@Autowired
	VC4Token vc4Token;
	
	@Autowired
	SplitterPort port ;
	
	@Autowired
	SplitterPortResponse portResponse ;
	
	@Autowired
	UpSplitterResponse upSplitterRes;
	
	@Autowired
	NeCabinetAlarmJobRepository cabinetAlarmJobRepo;
	
	@Autowired
	NeBoxAlarmJobRepository boxAlarmJobRepo;
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse>  GetExchCabList(String vc4Tocken,String exchCode){
		List<Cabinet> exchCabinets = new ArrayList<Cabinet>();
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_CABINET_LIST/filtered/EXCHCODE== \""+exchCode+"\"")
				  .method("GET", null)
				  .addHeader("Authorization", passToken)
				  .build();
		try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			responseCode = response.code();
			System.out.println("response code: "+response.code());
			if(responseCode == 401) {
				vc4Token.token = vc4Token.getVc4Token();
				vc4Tocken = vc4Token.token;
				passToken = "Bearer "+vc4Tocken;
				request = new Request.Builder()
						  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_CABINET_LIST/filtered/EXCHCODE== \""+exchCode+"\"")
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			String str = response.body().string();
			System.out.println("response exch cab list: "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_CABINET_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_CABINET_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   exchCabinets = mapper.readValue(str, new TypeReference<List<Cabinet>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(exchCabinets);
				}
				
				if(exchCabinets != null)
					apiResponse.setClientMessage("Success");
				else
					apiResponse.setClientMessage("No object found");
            }
			if(responseCode == 404) {
				apiResponse.setStatus(HttpStatus.NOT_FOUND);
				apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponse.setClientMessage(responseMsg);
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			apiResponse.setClientMessage("An error occured while fetching audit data");
			return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse>  GetCabSplitterList(String vc4Tocken,String cabId){
		List<Splitter> cabSplitters = new ArrayList<Splitter>();
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_CABINET_SPLITTERS_LIST/filtered/cabinetid== "+cabId)
				  .method("GET", null)
				  .addHeader("Authorization", passToken)
				  .build();
		try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			responseCode = response.code();
			System.out.println("response code: "+response.code());
			if(responseCode == 401) {
				vc4Token.token = vc4Token.getVc4Token();
				vc4Tocken = vc4Token.token;
				passToken = "Bearer "+vc4Tocken;
				request = new Request.Builder()
						  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_CABINET_SPLITTERS_LIST/filtered/cabinetid== "+cabId)
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			String str = response.body().string();
			System.out.println("response  GetCabSplitterList: "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_CABINET_SPLITTERS_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_CABINET_SPLITTERS_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   cabSplitters = mapper.readValue(str, new TypeReference<List<Splitter>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(cabSplitters);
				}
				
				if(cabSplitters != null)
					apiResponse.setClientMessage("Success");
				else
					apiResponse.setClientMessage("No object found");
            }
			if(responseCode == 404) {
				apiResponse.setStatus(HttpStatus.NOT_FOUND);
				apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponse.setClientMessage(responseMsg);
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			apiResponse.setClientMessage("An error occured while fetching audit data");
			return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse>  GetCabTBoxList(String vc4Tocken,String cabId){
		List<TBox> cabTBoxs = new ArrayList<TBox>();
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_TB_SPLITTERS_LIST/filtered/cabinetid== "+cabId)
				  .method("GET", null)
				  .addHeader("Authorization", passToken)
				  .build();
		try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			responseCode = response.code();
			System.out.println("response code: "+response.code());
			if(responseCode == 401) {
				vc4Token.token = vc4Token.getVc4Token();
				vc4Tocken = vc4Token.token;
				passToken = "Bearer "+vc4Tocken;
				request = new Request.Builder()
						  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_TB_SPLITTERS_LIST/filtered/cabinetid== "+cabId)
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			String str = response.body().string();
			System.out.println("response GetCabTBoxList : "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_TB_SPLITTERS_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_TB_SPLITTERS_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   cabTBoxs = mapper.readValue(str, new TypeReference<List<TBox>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(cabTBoxs);
				}
				
				if(cabTBoxs != null)
					apiResponse.setClientMessage("Success");
				else
					apiResponse.setClientMessage("No object found");
            }
			if(responseCode == 404) {
				apiResponse.setStatus(HttpStatus.NOT_FOUND);
				apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponse.setClientMessage(responseMsg);
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			apiResponse.setClientMessage("An error occured while fetching audit data");
			return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetSplitterPortList(String vc4Tocken,String splitterId){
//		List<SplitterPort> spPorts = new ArrayList<SplitterPort>();
		 
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		JSONObject bodyJson = new JSONObject();
        String bodyJsonStr = "";
        
		passToken = "Bearer "+vc4Tocken;
		bodyJson.put("NODE_ID", splitterId);

        bodyJsonStr = bodyJson.toString();
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, bodyJsonStr);
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/GetNodePorts")
				  .post(body)
				  .addHeader("Authorization", passToken)
				  .build();
		try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			responseCode = response.code();
			System.out.println("response code: "+response.code());
			if(responseCode == 401) {
				vc4Token.token = vc4Token.getVc4Token();
				vc4Tocken = vc4Token.token;
				passToken = "Bearer "+vc4Tocken;
				request = new Request.Builder()
						  .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/GetNodePorts")
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			String str = response.body().string();
			System.out.println("response GetSplitterPortList : "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_TB_SPLITTERS_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_TB_SPLITTERS_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
					ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    portResponse  = mapper.readValue(str, SplitterPortResponse.class);

                    apiResponse.setStatus(HttpStatus.OK);
                    apiResponse.setStatusCode(HttpStatus.OK.value());
                    apiResponse.setBody(portResponse);
				}
				
				if(portResponse != null)
					apiResponse.setClientMessage("Success");
				else
					apiResponse.setClientMessage("No object found");
            }
			if(responseCode == 404) {
				apiResponse.setStatus(HttpStatus.NOT_FOUND);
				apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponse.setClientMessage(responseMsg);
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
			if(responseCode == 500) {
				apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				apiResponse.setClientMessage("Exception occurs. Could not execute ExecuteProcedure with Procedure:GetNodePorts.");
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			apiResponse.setClientMessage("An error occured while fetching audit data");
			return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetCabinetAlarmJobs(Long vc4Id){
		APIResponse apiResponse=new APIResponse();
		List<NeCabinetAlarmJob> cabinetJobs = new ArrayList<NeCabinetAlarmJob>();
		
		cabinetJobs=  cabinetAlarmJobRepo.findCabinetJobsByVc4Id(vc4Id);
		if(cabinetJobs == null || cabinetJobs.isEmpty())
			apiResponse.setClientMessage("NO_DATA_FOUND");
		else
			apiResponse.setClientMessage("Success");
		
		apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(cabinetJobs);
        
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetCabinetBoxAlarmJobs(Long vc4Id){
		APIResponse apiResponse=new APIResponse();
		List<NeBoxAlarmJob> boxJobs = new ArrayList<NeBoxAlarmJob>();
		
		boxJobs=  boxAlarmJobRepo.findBoxJobsByVc4Id(vc4Id);
		if(boxJobs == null || boxJobs.isEmpty())
			apiResponse.setClientMessage("NO_DATA_FOUND");
		else
			apiResponse.setClientMessage("Success");
		
		apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(boxJobs);
        
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetSplitterTypes(String vc4Tocken){
		List<SplitterType> splitterTypes = new ArrayList<SplitterType>();
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_SLITTERS_TYPES_LIST/filtered/ID>0")
				  .method("GET", null)
				  .addHeader("Authorization", passToken)
				  .build();
		try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			responseCode = response.code();
			System.out.println("response code: "+response.code());
			if(responseCode == 401) {
				vc4Token.token = vc4Token.getVc4Token();
				vc4Tocken = vc4Token.token;
				passToken = "Bearer "+vc4Tocken;
				request = new Request.Builder()
						  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_SLITTERS_TYPES_LIST/filtered/ID>0")
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			String str = response.body().string();
			System.out.println("response GetSplitterTypes: "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_CABINET_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_CABINET_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   splitterTypes = mapper.readValue(str, new TypeReference<List<SplitterType>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(splitterTypes);
				}
				
				if(splitterTypes != null)
					apiResponse.setClientMessage("Success");
				else
					apiResponse.setClientMessage("No object found");
            }
			if(responseCode == 404) {
				apiResponse.setStatus(HttpStatus.NOT_FOUND);
				apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponse.setClientMessage(responseMsg);
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			apiResponse.setClientMessage("An error occured while fetching audit data");
			return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> UpdateSplitter(String vc4Tocken,UpSplitter splitter){
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("application/json");

		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"EXCH_CODE\": \""+splitter.getEXCH_CODE()+"\",\r\n    \"CABINET_NO\": \""+splitter.getCABINET_NO()+"\",\r\n    \"SPLITTER_ID\": \""+splitter.getSPLITTER_ID()+"\",\r\n    \"GPON_ID\": \""+splitter.getGPON_ID()+"\",\r\n    \"GPON_SHELF\": \""+splitter.getGPON_SHELF()+"\",\r\n    \"GPON_CARD\": \""+splitter.getGPON_CARD()+"\",\r\n    \"GPON_PORT\": \""+splitter.getGPON_PORT()+"\",\r\n    \"MMR_A_ODF\": \""+splitter.getMMR_A_ODF()+"\",\r\n    \"MMR_A_PORT\": \""+splitter.getMMR_A_PORT()+"\",\r\n    \"MMR_P_ODF\": \""+splitter.getMMR_P_ODF()+"\",\r\n    \"MMR_P_PORT\": \""+splitter.getMMR_P_PORT()+"\"\r\n}");
		Request request = new Request.Builder()
			      .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/UpdateFCCSplitter")
				  .method("POST", body)
				  .addHeader("Authorization", passToken)
				  .addHeader("Content-Type", "application/json")
				  .build();
		System.out.println("updateSplitter request : " +request.toString());
		System.out.println("req body : "+ request.body().toString());
		try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			responseCode = response.code();
			System.out.println("response code: "+response.code());
			if(responseCode == 401) {
				vc4Token.token = vc4Token.getVc4Token();
				vc4Tocken = vc4Token.token;
				passToken = "Bearer "+vc4Tocken;
				request = new Request.Builder()
						  .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/UpdateFCCSplitter")
						  .method("POST", body)
						  .addHeader("Authorization", passToken)
						  .addHeader("Content-Type", "application/json")
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			
			String str = response.body().string();
			System.out.println("response UpdateSplitter: "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_GPON_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_GPON_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   upSplitterRes = mapper.readValue(str, UpSplitterResponse.class);
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(upSplitterRes);
				}
				
				if(upSplitterRes != null)
					apiResponse.setClientMessage("Success");
				else
					apiResponse.setClientMessage("No object found");
            }
			if(responseCode == 404) {
				apiResponse.setStatus(HttpStatus.NOT_FOUND);
				apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponse.setClientMessage(responseMsg);
				apiResponse.setBody(null);
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
			if(responseCode == 500) {
				apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				apiResponse.setClientMessage("Exception occurs. Could not execute ExecuteProcedure with Procedure:UpdateFCCSplitter.");
				apiResponse.setBody(null);
				return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			apiResponse.setClientMessage("An error occured while fetching audit data");
			return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}

}
