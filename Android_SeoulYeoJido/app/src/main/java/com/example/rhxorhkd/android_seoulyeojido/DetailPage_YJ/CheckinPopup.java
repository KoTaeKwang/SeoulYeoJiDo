package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.rhxorhkd.android_seoulyeojido.ChangeInfo;
import com.example.rhxorhkd.android_seoulyeojido.R;

/**
 * Created by hanyoojin on 2016. 10. 27..
 */

public class CheckinPopup extends Activity implements View.OnClickListener {
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 삭제
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkinpopup);
        result = getIntent().getStringExtra("result");
        findViewById(R.id.btn_checkincancel).setOnClickListener(this);
        findViewById(R.id.btn_checkin).setOnClickListener(this);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_checkincancel:
                this.finish();
                break;
            case R.id.btn_checkin:
                //Toast.makeText(getApplicationContext(), "눌림", Toast.LENGTH_LONG).show();
                if(result.equals("1"))
                startActivity(new Intent(this, CheckinOK.class));

                finish();
                //Intent i = new Intent(this, CheckinOK.class);
                //startActivity(i);
                break;
            default: break;
        }
    }
}

//public class Dialog extends Activity implements View.OnClickListener {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 삭제
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dialog);
//
//        findViewById(R.id.btn_reviewCancel).setOnClickListener(this);
//    }
//
//    public void onClick(View v){
//        switch (v.getId()){
//            case R.id.btn_reviewCancel:
//                this.finish();
//                break;
//            case R.id.btn_reviewSave:
//
//        }
//
//    }
//}

