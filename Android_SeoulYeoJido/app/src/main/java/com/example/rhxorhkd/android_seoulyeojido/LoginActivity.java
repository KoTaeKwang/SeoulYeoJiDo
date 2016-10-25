package com.example.rhxorhkd.android_seoulyeojido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email, pw1, pw2;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.email);
        pw1 = (EditText)findViewById(R.id.pw1);
        pw2 = (EditText)findViewById(R.id.pw2);

        findViewById(R.id.submit).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit :
                Toast.makeText(this, "로그인", Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
    }
}
