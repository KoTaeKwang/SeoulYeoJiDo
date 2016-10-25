package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.rhxorhkd.android_seoulyeojido.R;

/**
 * Created by hanyoojin on 2016. 10. 25..
 */

public class Dialog extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 삭제
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        findViewById(R.id.btn_reviewCancel).setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_reviewCancel:
                this.finish();
                break;
            case R.id.btn_reviewSave:

        }

    }
}
