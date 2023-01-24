package mts.ftth.vc4.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mts.ftth.vc4.services.apiInterface.FullCrossConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mts.ftth.vc4.controllers.VC4Token;
import mts.ftth.vc4.models.CrossConnection;
import mts.ftth.vc4.payload.response.APIResponse;
import mts.ftth.vc4.security.SSLTool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class FullCrossConnectionServiceImpl implements FullCrossConnectionService {
	@Autowired
	VC4Token vc4Token;
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<APIResponse>  getFullCrossConnection(String vc4Tocken,String exchCode,int paginatorStartElement, int paginatorNumberOfElements){
		List<CrossConnection> crossConnList = new ArrayList<CrossConnection>();
		String passToken ="";
		SSLTool sl = new SSLTool();
		int responseCode = 0;
		String responseMsg = "";
		APIResponse apiResponse=new APIResponse();
		
		passToken = "Bearer "+vc4Tocken;
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				  .url(vc4Token.getUrl()+"/api/ims/TEAPI_FULLCROSSCONNECTIONS/filtered/ID>0 and EXCHCODE == \""+exchCode+"\"?PaginatorStartElement="+paginatorStartElement+"&PaginatorNumberOfElements="+paginatorNumberOfElements)
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
				   		  .url(vc4Token.getUrl()+"/api/ims/TEAPI_FULLCROSSCONNECTIONS/filtered/ID>0 and EXCHCODE == \""+exchCode+"\"?PaginatorStartElement="+paginatorStartElement+"&PaginatorNumberOfElements="+paginatorNumberOfElements)
						  .method("GET", null)
						  .addHeader("Authorization", passToken)
						  .build();
				
				client = sl.getUnsafeOkHttpClient();
			    response = client.newCall(request).execute();
			}
			
			responseMsg =response.message();
			String str = response.body().string();
			System.out.println("response getFullCrossConnection: "+str);
			
			if (!str.equals("") ) {
				System.out.println("not empty");
				if(str.equals("No records found for Entity:TEAPI_FULLCROSSCONNECTIONS")) {
					apiResponse.setStatus(HttpStatus.OK);
					apiResponse.setStatusCode(HttpStatus.OK.value());
					apiResponse.setClientMessage("No records found for Entity:TEAPI_FULLCROSSCONNECTIONS");
					return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
				}else {
                   ObjectMapper mapper = new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   crossConnList = mapper.readValue(str, new TypeReference<List<CrossConnection>>(){});
                   apiResponse.setStatus(HttpStatus.OK);
                   apiResponse.setStatusCode(HttpStatus.OK.value());
                   apiResponse.setBody(crossConnList);
				}
				
				if(crossConnList != null)
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
