package mts.ftth.vc4.services;


import mts.ftth.vc4.models.Cabinet;
import mts.ftth.vc4.security.SSLTool;
import okhttp3.OkHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenericRestClient<T, V> extends RestTemplate {
    public enum AuthType {
        BEARER,
        BASIC
    }

    private final RestTemplate restTemplate;

    public GenericRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GenericRestClient() {
        SSLTool sl = new SSLTool();
        OkHttpClient client = sl.getUnsafeOkHttpClient();

        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory(client);
        restTemplate = new RestTemplate(requestFactory);
    }

    public T get(String url, Class<T> responseType, V requestBody, String authToken, AuthType authType) {

        HttpHeaders headers = authToken(authToken, authType);

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(requestBody, headers), responseType).getBody();
    }


    public <T> List<T> getList(String url, Class<T[]> type, String authToken, AuthType authType) {
        HttpHeaders headers = authToken(authToken, authType);
        return Arrays.stream(restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), type).getBody()).collect(Collectors.toList());
    }


    public T post(String url, Class<T> responseType, V requestBody, String authToken, AuthType authType) {

        HttpHeaders headers = authToken(authToken, authType);

        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestBody, headers), responseType).getBody();
    }

    public HttpHeaders authToken(String authToken, AuthType authType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        if (authType == AuthType.BEARER) {
            headers.set("Authorization", "Bearer " + authToken);
        } else if (authType == AuthType.BASIC) {
            headers.set("Authorization", "Basic " + authToken);
        }
        return headers;
    }
}




