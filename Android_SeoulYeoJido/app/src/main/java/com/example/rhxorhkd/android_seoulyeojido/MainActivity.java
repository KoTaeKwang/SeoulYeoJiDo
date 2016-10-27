package com.example.rhxorhkd.android_seoulyeojido;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TabHost;

import com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ.CheckinmapActivity;

public class MainActivity extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent;
        TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup(getLocalActivityManager()); //intent를 content로 하기 위해서 getlocalActivitymanager 필요
        TabHost.TabSpec spec;
        Resources res= getResources();
        //setindicator -> 탭에 지시자로, 라벨과 아이콘 지정, setIndicator(CharSequence label, Drawable icon)
        //Setcontent -> 탭의 내용물 XML 문서의 뷰 이용, setContent(Intent intent) -> 인텐트를 이용하여 다른 액티비티 호출
        Drawable d = ContextCompat.getDrawable(this,R.drawable.home);
        intent = new Intent(this,home.class);
        spec = tabHost.newTabSpec("Tab1").setContent(intent).setIndicator("",d);
        tabHost.addTab(spec);

        Drawable c = ContextCompat.getDrawable(this,R.drawable.checkin);
        intent = new Intent(this,CheckinmapActivity.class);
        spec = tabHost.newTabSpec("Tab2").setContent(intent).setIndicator("",c);
        tabHost.addTab(spec);

        Drawable b = ContextCompat.getDrawable(this,R.drawable.rank);
        intent = new Intent(this,rank.class);
        spec = tabHost.newTabSpec("Tab3").setContent(intent).setIndicator("",b);
        tabHost.addTab(spec);

        Drawable a = ContextCompat.getDrawable(this,R.drawable.my);
        intent = new Intent(this,set.class);
        spec = tabHost.newTabSpec("Tab4").setContent(intent).setIndicator("",a);
        tabHost.addTab(spec);


        //높이설정
        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=210;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=210;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=210;
        tabHost.getTabWidget().getChildAt(3).getLayoutParams().height=210;


        tabHost.setCurrentTab(0); //첫 시작
    }
}
