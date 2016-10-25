package com.example.rhxorhkd.android_seoulyeojido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        findViewById(R.id.login_btn).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn :
                Toast.makeText(this, "로그인하기", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, LoginActivity.class);
                this.startActivity(i);
                break;
            default: break;
        }
    }
}
