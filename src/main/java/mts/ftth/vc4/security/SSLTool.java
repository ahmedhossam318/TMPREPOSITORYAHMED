package mts.ftth.vc4.security;

import javax.net.ssl.*;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;

import okhttp3.OkHttpClient;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SSLTool {

  public void disableCertificateValidation() {
    // Create a trust manager that does not validate certificate chains
    TrustManager[] trustAllCerts = new TrustManager[] { 
      new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() { 
          return new X509Certificate[0]; 
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
    }};

    // Ignore differences between given hostname and certificate hostname
    HostnameVerifier hv = new HostnameVerifier() {
      public boolean verify(String hostname, SSLSession session) { return true; }
    };

    // Install the all-trusting trust manager
    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      HttpsURLConnection.setDefaultHostnameVerifier(hv);
    } catch (Exception e) {}
  }
  
  
  public  OkHttpClient getUnsafeOkHttpClient() {
	  try {
	    // Create a trust manager that does not validate certificate chains
	    final TrustManager[] trustAllCerts = new TrustManager[] {
	        new X509TrustManager() {
	          @Override
	          public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
	          }

	          @Override
	          public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
	          }

	          @Override
	          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	            return new java.security.cert.X509Certificate[]{};
	          }
	        }
	    };

	    // Install the all-trusting trust manager
	    final SSLContext sslContext = SSLContext.getInstance("SSL");
	    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
	    // Create an ssl socket factory with our all-trusting manager
	    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

	    OkHttpClient.Builder builder = new OkHttpClient.Builder();
	    builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
	    builder.hostnameVerifier(new HostnameVerifier() {
	      @Override
	      public boolean verify(String hostname, SSLSession session) {
	        return true;
	      }
	    });

	    OkHttpClient okHttpClient = builder.build();
	    return okHttpClient;
	  } catch (Exception e) {
	    throw new RuntimeException(e);
	  }
	}
}
