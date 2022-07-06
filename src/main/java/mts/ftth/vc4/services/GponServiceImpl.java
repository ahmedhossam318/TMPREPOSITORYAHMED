package mts.ftth.vc4.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mts.ftth.vc4.controllers.VC4Token;
import mts.ftth.vc4.models.Gpon;
import mts.ftth.vc4.models.GponCard;
import mts.ftth.vc4.models.GponCardPort;
import mts.ftth.vc4.models.NeGponAlarmJob;
import mts.ftth.vc4.models.NeGponCardAlarmJob;
import mts.ftth.vc4.models.NeGponPortAlarmJob;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.repos.NeGponAlarmJobRepository;
import mts.ftth.vc4.repos.NeGponCardAlarmJobRepository;
import mts.ftth.vc4.repos.NeGponPortAlarmJobRepository;
import mts.ftth.vc4.repos.SysConfigRepo;
import mts.ftth.vc4.security.SSLTool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

@Service
public class GponServiceImpl implements GponService{
	@Autowired
	VC4Token vc4Token;
	
	@Autowired
	NeGponAlarmJobRepository gponAlarmJobRepo;
	
	@Autowired
	NeGponCardAlarmJobRepository gponCardAlarmJobRepo;

	@Autowired
	NeGponPortAlarmJobRepository gponPortAlarmJobRepo;
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetAllGpon(String vc4Tocken,int PaginatorStartElement , int PaginatorNumberOfElements,String exchCode){
		List<Gpon> gponList = new ArrayList<Gpon>();
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
			      .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_LIST/filtered/exchcode== \""+exchCode+"\"?PaginatorStartElement="+PaginatorStartElement+"&PaginatorNumberOfElements="+PaginatorNumberOfElements)
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
					      .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_LIST/filtered/exchcode== \""+exchCode+"\"?PaginatorStartElement="+PaginatorStartElement+"&PaginatorNumberOfElements="+PaginatorNumberOfElements)
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			
			String str = response.body().string();
			System.out.println("response gpon list: "+str);
			
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
                   gponList = mapper.readValue(str, new TypeReference<List<Gpon>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(gponList);
				}
				
				if(gponList != null)
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
	public ResponseEntity<APIResponse> GetGponCards(String vc4Tocken,String gponId){
		List<GponCard> gponCards = new ArrayList<GponCard>();
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
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_CARDS_LIST/filtered/GPONID == \""+gponId+"\"")
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
						  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_CARDS_LIST/filtered/GPONID == \""+gponId+"\"")
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			
			String str = response.body().string();
			System.out.println("response gpon Card: "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_GPON_CARDS_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_GPON_CARDS_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   gponCards = mapper.readValue(str, new TypeReference<List<GponCard>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(gponCards);
				}
				
				if(gponCards != null)
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
	public ResponseEntity<APIResponse> GetGponCardPorts(String vc4Tocken,String cardId){
		List<GponCardPort> gponCardPorts = new ArrayList<GponCardPort>();
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
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_CARDS_PORTS_LIST/filtered/NODECARDID== "+cardId)
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
						  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_CARDS_PORTS_LIST/filtered/NODECARDID== "+cardId)
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			String str = response.body().string();
			System.out.println("response gpon Card ports: "+str);
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_CARDS_PORTS_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_CARDS_PORTS_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   gponCardPorts = mapper.readValue(str, new TypeReference<List<GponCardPort>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(gponCardPorts);
				}
				
				if(gponCardPorts != null)
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
	public ResponseEntity<APIResponse> GetGponAlarmJobs(Long vc4Id){
		APIResponse apiResponse=new APIResponse();
		List<NeGponAlarmJob> gponJobs = new ArrayList<NeGponAlarmJob>();
		
		gponJobs=  gponAlarmJobRepo.findGponJobsByVc4Id(vc4Id);
		System.out.println("gponJobs.isEmpty() : "+gponJobs.isEmpty());
		if(gponJobs == null || gponJobs.isEmpty())
			apiResponse.setClientMessage("NO_DATA_FOUND");
		else
			apiResponse.setClientMessage("Success");
		
		apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(gponJobs);
        
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetGponCardAlarmJobs(Long vc4Id){
		APIResponse apiResponse=new APIResponse();
		List<NeGponCardAlarmJob> gponCardJobs = new ArrayList<NeGponCardAlarmJob>();
		
		gponCardJobs=  gponCardAlarmJobRepo.findGponCardJobsByVc4Id(vc4Id);
		if(gponCardJobs == null || gponCardJobs.isEmpty())
			apiResponse.setClientMessage("NO_DATA_FOUND");
		else
			apiResponse.setClientMessage("Success");
		
		apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(gponCardJobs);
        
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetGponPortAlarmJobs(Long vc4Id){
		APIResponse apiResponse=new APIResponse();
		List<NeGponPortAlarmJob> gponPortJobs = new ArrayList<NeGponPortAlarmJob>();
		
		gponPortJobs=  gponPortAlarmJobRepo.findGponPortJobsByVc4Id(vc4Id);
		if(gponPortJobs == null || gponPortJobs.isEmpty())
			apiResponse.setClientMessage("NO_DATA_FOUND");
		else
			apiResponse.setClientMessage("Success");
		
		apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(gponPortJobs);
        
		return  new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse> GetGponByPopId(String vc4Tocken,int PaginatorStartElement , int PaginatorNumberOfElements,String popId){
		List<Gpon> gponList = new ArrayList<Gpon>();
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
			      .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_LIST/filtered/exchcode== \""+popId+"\"?PaginatorStartElement="+PaginatorStartElement+"&PaginatorNumberOfElements="+PaginatorNumberOfElements)
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
					      .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_LIST/filtered/exchcode== \""+popId+"\"?PaginatorStartElement="+PaginatorStartElement+"&PaginatorNumberOfElements="+PaginatorNumberOfElements)
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			
			String str = response.body().string();
			System.out.println("response gpon list by popId: "+str);
			
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
                   gponList = mapper.readValue(str, new TypeReference<List<Gpon>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(gponList);
				}
				
				if(gponList != null)
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
	public ResponseEntity<APIResponse> GetCardsByGponNodeId(String vc4Tocken,String gponNodeId){
		List<GponCard> gponCards = new ArrayList<GponCard>();
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
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_CARDS_LIST/filtered/GPONNODEID == \""+gponNodeId+"\"")
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
						  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_CARDS_LIST/filtered/GPONNODEID == \""+gponNodeId+"\"")
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			
			String str = response.body().string();
			System.out.println("response gpon Card by gpon node id: "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_GET_GPON_CARDS_LIST")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_GET_GPON_CARDS_LIST");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   gponCards = mapper.readValue(str, new TypeReference<List<GponCard>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(gponCards);
				}
				
				if(gponCards != null)
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
}
