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
import mts.ftth.vc4.models.Cabinet;
import mts.ftth.vc4.models.GponCard;
import mts.ftth.vc4.models.Splitter;
import mts.ftth.vc4.models.TBox;
import mts.ftth.vc4.payload.response.APIResponse;
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
			responseMsg =response.message();
			System.out.println("response code: "+response.code());
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
			responseMsg =response.message();
			System.out.println("response code: "+response.code());
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
			responseMsg =response.message();
			System.out.println("response code: "+response.code());
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

}