package ru.android_studio.gibdd_servicedit;

import org.json.JSONObject;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static java.net.Proxy.Type.HTTP;


/**
 * Created by Lion on 18.01.2017.
 */

public class RestPost extends Thread{

    private String uri;
    private Class cl;
    private JSONObject jsonObject;

    public RestPost(String uri, Class cl, JSONObject jsonObject) {
        this.uri = uri;
        this.cl = cl;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentEncoding(ContentCodingType.parseCodingType("UTF-8"));
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(),headers);
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        RequestResult.INSTANCE.jsonResult = new String(response.getBody().getBytes(), Charset.forName("UTF-8"));
    }

}
