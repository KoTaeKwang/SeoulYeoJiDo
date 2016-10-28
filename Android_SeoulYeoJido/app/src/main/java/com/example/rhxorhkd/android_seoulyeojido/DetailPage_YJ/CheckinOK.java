package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.rhxorhkd.android_seoulyeojido.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hanyoojin on 2016. 10. 27..
 */

public class CheckinOK extends Activity {

    private TimerTask mTask;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 삭제
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkinok);

        mTask = new TimerTask() {
            @Override
            public void run() {
                /*Intent intent = new Intent(getApplicationContext()
                        , DetailActivity.class);
                startActivity(intent);*/
                finish();
            }
        };

        mTimer = new Timer();

        mTimer.schedule(mTask, 2000);
       // mTimer.schedule(mTask, 3000, 5000);


        //findViewById(R.id.btn_checkincancel).setOnClickListener(this);



    }

        @Override
    protected void onDestroy() {
        Log.i("test", "onDstory()");
        mTimer.cancel();
        super.onDestroy();
    }


}
