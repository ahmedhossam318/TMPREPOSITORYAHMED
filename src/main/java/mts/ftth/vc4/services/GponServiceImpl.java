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
import mts.ftth.vc4.payload.response.APIResponse;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gpon>GetAllGpon(String vc4Tocken,int PaginatorStartElement , int PaginatorNumberOfElements,String exchCode){
		List<Gpon> gponList = new ArrayList<Gpon>();
		String passToken ="";
		SSLTool sl = new SSLTool();
		passToken = "Bearer "+vc4Tocken;
		System.out.println("passToken :" +passToken);		/// cal get GPON List VC4
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_GPON_LIST/filtered/exchcode== \""+exchCode+"\"?PaginatorStartElement="+PaginatorStartElement+"&PaginatorNumberOfElements="+PaginatorNumberOfElements)
				  .method("GET",null)
				  .addHeader("Authorization", passToken)
				  .build();
		
        try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			String str = response.body().string();
			System.out.println("response : "+str);
			if (!str.equals("")) {
                //JSONObject attr = new JSONObject(str);
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                gponList = mapper.readValue(str, new TypeReference<List<Gpon>>(){}); 
               
            }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return gponList;
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
			responseMsg =response.message();
			System.out.println("response code: "+response.code());
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
//		Request request = new Request.Builder()
//				  .url("https://10.98.35.78:2080/api/ims/TEAPI_GET_CARDS_PORTS_LIST/filtered/NODECARDID== 23728")
//				  .method("GET", body)
//				  .addHeader("Authorization", "Bearer 3539ae85-8be0-45f4-a31c-c88b66a729e4")
//				  .build();
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_GET_CARDS_PORTS_LIST/filtered/NODECARDID== "+cardId)
				  .method("GET", null)
				  .addHeader("Authorization", passToken)
				  .build();
		try {
			client = sl.getUnsafeOkHttpClient();
			Response response = client.newCall(request).execute();
			
			responseCode = response.code();
			responseMsg =response.message();
			System.out.println("response code: "+response.code());
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
}
