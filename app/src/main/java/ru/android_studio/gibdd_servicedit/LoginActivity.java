package ru.android_studio.gibdd_servicedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String uri = "http://api.pub.emp.msk.ru:8081/json/v10.0/citizens/auth/loginbymsisdn/";
    private String token = "5b25aa910a975eda16dc66dbfca86b86";

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
        RequestResult.INSTANCE.refreshResult();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin: {
                final JSONObject jsonObject = new JSONObject();
                try {
                    TextView tvMsisdn = (TextView) findViewById(R.id.phoneLogin);
                    TextView tvPassword = (TextView) findViewById(R.id.passwordLogin);
                    jsonObject.put("token", token);
                    jsonObject.put("msisdn", tvMsisdn.getText());
                    jsonObject.put("password", tvPassword.getText());
                    RestPost rest = new RestPost(uri, String.class, jsonObject);
                    rest.start();
                    rest.join();
                    Intent intent = new Intent(this, BetaResultActivity.class);
                    intent.putExtra("result", RequestResult.INSTANCE.jsonResult);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }break;
        }
    }

}
