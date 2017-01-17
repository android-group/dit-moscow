package ru.android_studio.gibdd_servicedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String uri = "http://api.pub.emp.msk.ru:8081/json/v10.0/citizens/auth/loginbymsisdn";
    private String token = "";
    private String msisdn = "79777328501";
    private String password = "112358";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        result = "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin: {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", token);
                    jsonObject.put("msisdn", msisdn);
                    jsonObject.put("password", password);
                    new Thread( () -> {
                        RestTemplate restTemplate = new RestTemplate();
                        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                        setResult(restTemplate.getForObject(uri, String.class, jsonObject));
                    }).start();
                    while (result.equals("")) {
                        Thread.currentThread().sleep(100);
                    }
                    Intent intent = new Intent(this, BetaResultActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }break;
        }
    }

    public void setResult(String s) {
        this.result = s;
    }


}
