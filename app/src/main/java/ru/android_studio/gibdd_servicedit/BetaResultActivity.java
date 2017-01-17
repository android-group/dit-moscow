package ru.android_studio.gibdd_servicedit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BetaResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_result);
        TextView tv = (TextView) findViewById(R.id.tvBetaResult);
        Intent intent = getIntent();
        tv.setText(intent.getStringExtra("result"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
