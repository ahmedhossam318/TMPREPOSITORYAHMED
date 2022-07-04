package mts.ftth.vc4.services;

import java.io.IOException;
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
import mts.ftth.vc4.models.ActiveLineDataResponse;
import mts.ftth.vc4.models.Gpon;
import mts.ftth.vc4.models.PassiveLineDataResponse;
import mts.ftth.vc4.models.SplitterPortResponse;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.security.SSLTool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class LineCardServiceImpl implements LineCardService{
	
	@Autowired
	VC4Token vc4Token;
	
	@Autowired
	ActiveLineDataResponse activeResp;
	
	@Autowired
	PassiveLineDataResponse passiveResp;
	
	@SuppressWarnings("unchecked")
	@Override
    public ResponseEntity<APIResponse> getActiveDataLineCard(String vc4Tocken,String cityCode,String telNo){ 
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"CITY_CODE\": \""+cityCode+"\",\r\n    \"TEL_NO\": \""+telNo+"\"\r\n}");
		Request request = new Request.Builder()
			      .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/QueryLineActiveInfo")
				  .method("POST", body)
				  .addHeader("Authorization", passToken)
				  .addHeader("Content-Type", "application/json")
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
						  .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/QueryLineActiveInfo")
						  .method("POST", body)
						  .addHeader("Authorization", passToken)
						  .addHeader("Content-Type", "application/json")
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			
			String str = response.body().string();
			System.out.println("response get Active data: "+str);
			
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
                   activeResp = mapper.readValue(str, ActiveLineDataResponse.class);
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(activeResp);
				}
				
				if(activeResp != null)
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
				apiResponse.setClientMessage("Exception occurs. Could not execute ExecuteProcedure with Procedure:QueryLineActiveInfo.");
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


	
	@SuppressWarnings("unchecked")
	@Override
    public ResponseEntity<APIResponse> getPassiveDataLineCard(String vc4Tocken,String cityCode,String telNo){ 
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"CITY_CODE\": \""+cityCode+"\",\r\n    \"TEL_NO\": \""+telNo+"\"\r\n}");
		Request request = new Request.Builder()
			      .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/QueryLinePassiveInfo")
				  .method("POST", body)
				  .addHeader("Authorization", passToken)
				  .addHeader("Content-Type", "application/json")
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
						  .url(vc4Token.getUrl()+"/api/ims/CustomDataOperation/ExecuteProcedure/QueryLinePassiveInfo")
						  .method("POST", body)
						  .addHeader("Authorization", passToken)
						  .addHeader("Content-Type", "application/json")
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			
			String str = response.body().string();
			System.out.println("response get Passive data: "+str);
			
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
                   passiveResp = mapper.readValue(str, PassiveLineDataResponse.class);
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(passiveResp);
				}
				
				if(activeResp != null)
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
				apiResponse.setClientMessage("Exception occurs. Could not execute ExecuteProcedure with Procedure:QueryLinePassiveInfo.");
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