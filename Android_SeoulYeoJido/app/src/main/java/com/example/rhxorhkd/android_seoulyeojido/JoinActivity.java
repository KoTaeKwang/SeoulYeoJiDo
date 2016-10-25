package com.example.rhxorhkd.android_seoulyeojido;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhxorhkd.android_seoulyeojido.Validator.EmailValidator;
import com.example.rhxorhkd.android_seoulyeojido.Validator.NickNameValidator;
import com.example.rhxorhkd.android_seoulyeojido.Validator.PasswordValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText email, nickName, pw1, pw2;
    private FirebaseDatabase db;
    private DatabaseReference Ref;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        Ref = db.getReference().child("member");

        email = (EditText)findViewById(R.id.join_email);
        nickName = (EditText)findViewById(R.id.nickname);
        pw1 = (EditText)findViewById(R.id.pw1);
        pw2 = (EditText)findViewById(R.id.pw2);


        findViewById(R.id.join_btn_back).setOnClickListener(this);
        findViewById(R.id.done).setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.join_btn_back :
                finish();
                break;
            case R.id.done :
                if( !EmailValidator.getInstance().isValid(email.getText().toString()) )
                    Toast.makeText(getApplicationContext(),"이메일 형식이 올바르지 않습니다.",Toast.LENGTH_LONG).show();
                else if ( !NickNameValidator.getInstance().validate(nickName.getText().toString(),2,12) )
                    Toast.makeText(getApplicationContext(),"닉네임 형식이 바르지 않습니다(특수문자제외 2~12자)",Toast.LENGTH_LONG).show();
                else if( !PasswordValidator.getInstance().tvtalkValidate(pw1.getText().toString()))
                    Toast.makeText(this, "비밀번호가 올바르지 않습니다(영문, 숫자 조합 6~12자", Toast.LENGTH_LONG).show();
                else if(  !pw1.getText().toString().equals(pw2.getText().toString()) )
                    Toast.makeText(this, "비밀번호 확인이 올바르지 않습니다.", Toast.LENGTH_LONG).show();
                else {
                    auth.createUserWithEmailAndPassword(email.getText().toString(),pw2.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(JoinActivity.this, "이미 가입된 이메일입니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                final FirebaseUser user = auth.getCurrentUser();
                                UserProfileChangeRequest nickUpdate = new UserProfileChangeRequest.Builder() .setDisplayName(nickName.getText().toString()).build();
                                user.updateProfile(nickUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task1) {
                                        if (task1.isSuccessful()) {
                                            Ref.child(user.getUid() + "/email").setValue(email);
                                            Ref.child(user.getUid() + "/nickname").setValue(nickName);
                                            Ref.child(user.getUid() + "/profile").setValue(user.getPhotoUrl());
                                            Ref.child(user.getUid() + "/facebook").setValue(false);
                                            startActivity(new Intent(JoinActivity.this, MainActivity.class));
                                            Toast.makeText(JoinActivity.this, "가입완료", Toast.LENGTH_LONG).show();


                                        } else{
                                            Toast.makeText(JoinActivity.this, "닉네임등록 에러", Toast.LENGTH_LONG).show();
                                            Toast.makeText(JoinActivity.this, ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("왜안대", ""+e);
                                    }
                                });
                            }
                        }
                    });
                }
                break;
            default: break;
        }

    }
}
