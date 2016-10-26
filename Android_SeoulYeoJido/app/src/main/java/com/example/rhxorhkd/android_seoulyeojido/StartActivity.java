package com.example.rhxorhkd.android_seoulyeojido;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ActionBar ab = getSupportActionBar();
        ab.hide();


        findViewById(R.id.login_btn).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn :
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default: break;
        }
    }
}
