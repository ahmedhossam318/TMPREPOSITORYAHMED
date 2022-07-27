package mts.ftth.vc4.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import okhttp3.Credentials;
import mts.ftth.vc4.models.SYS_CONFIG;
import mts.ftth.vc4.repos.SysConfigRepo;
import mts.ftth.vc4.security.SSLTool;
import mts.ftth.vc4.services.SysConfigVC4Service;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class VC4Token implements WebMvcConfigurer {

//	@Value("${vc4.userName}")
//	private String userName ;
//	@Value("${vc4.password}")
//	private String password ;
	public static String token;
	
	@Autowired
	private Environment env;
	
	@Autowired
	SysConfigRepo sysConfigRepo;
	
	private String url ;
	
	public String getVc4Token() {
		String token = "";
		String userName = "";
		String password = "";
		SSLTool sl = new SSLTool();
		List<SYS_CONFIG> sysConfigVc4List= new ArrayList<SYS_CONFIG>();
		sysConfigVc4List = sysConfigRepo.GetAllSysConfig();
		userName = sysConfigVc4List.get(0).getVC4_USERNAME();
		password = sysConfigVc4List.get(0).getVC4_PASSWORD();
		setUrl(sysConfigVc4List.get(0).getVC4_URL());
		
		System.out.println("userName db :"+ userName);
		System.out.println("password db :"+ password);
		System.out.println("Vc4 URl db :"+ getUrl());
		
		try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("text/plain");

            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
              .url(getUrl() + "/api/Login")
              .method("POST", body)
              .addHeader("Authorization",  Credentials.basic(userName, password))
              .addHeader("Content-Type", "application/x-www-form-urlencoded")
              .build();
            System.out.println("Vc4 Login Request : "+ request.toString());
            client = sl.getUnsafeOkHttpClient();
            Response response = client.newCall(request).execute();
            
            token = response.body().string();
            
            System.out.println("Response Sstr token: " + token);
//            if (!Str.equals("")) {
//                org.json.JSONObject attr = new org.json.JSONObject(Str);
//                token= (String) attr.get("access_token");
//                System.out.println("gen Token:"+token);
//            }  
//            Configuration.releaseRootApplicationModule(am, false);
        }catch(Exception e){
            e.printStackTrace();
            token = "Fail"; 
        }

		return token;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@PostConstruct
    private void postConstruct() {
        token = getVc4Token();
    }

}
