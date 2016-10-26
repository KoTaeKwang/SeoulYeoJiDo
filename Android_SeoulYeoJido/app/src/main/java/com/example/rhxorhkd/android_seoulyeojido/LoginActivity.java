package com.example.rhxorhkd.android_seoulyeojido;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email,pw;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        auth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.email);
        pw = (EditText)findViewById(R.id.pw);

        findViewById(R.id.login_btn_back).setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.go_join).setOnClickListener(this);
        findViewById(R.id.find_pw).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.go_join :
                startActivity(new Intent(this, JoinActivity.class));
                break;
            case R.id.submit :
                auth.signInWithEmailAndPassword(email.getText().toString(), pw.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                }else{
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                            }
                        });


                break;
            case R.id.login_btn_back :
                finish();
                break;
            case R.id.find_pw :
                break;
            default: break;
        }
    }
}
