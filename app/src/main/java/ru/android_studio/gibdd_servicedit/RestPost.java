package ru.android_studio.gibdd_servicedit;

import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        RequestResult.INSTANCE.jsonResult = restTemplate.getForObject(uri, String.class, jsonObject);
    }

}
