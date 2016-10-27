package com.example.rhxorhkd.android_seoulyeojido;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ.DetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class home extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private FirebaseUser user;

    ImageView mapview1;
    ImageView mapview2;
    ImageView mapview3;
    ImageView mapview4;
    ImageView mapview5;
    ImageView mapview6;
    ImageView mapview7;
    ImageView mapview8;
    ImageView mapview9;
    ImageView mapview10;
    ImageView mapview11;
    ImageView mapview12;

    ListviewAdapter adapter; //listview adapter
    SearchitemAdapter sadapter; //search adapter
    RelativeLayout map; //지도
    LinearLayout searchlistview; //검색창누르면 나오는 리스트뷰
    Button button; // 지도 안의 버튼
    SearchView searchView; //서치뷰
    ListView lv; //리스트
    ArrayList<Searchitem> searchdatas; //서치 데이터들
    ListView listView1; //서치 리스트뷰들
    OkHttpClient client;
    private static final String TAG = "DemoActivity";
    private SlidingUpPanelLayout mLayout;
    JSONObject jsonobject;
    JSONArray jsonarray;
    Response response;
    Request request;
    public static String guNum;
    int guNumber_1, guNumber_2, guNumber_3, guNumber_4, guNumber_5, guNumber_6, guNumber_7,
            guNumber_8, guNumber_9, guNumber_10, guNumber_11;
    ImageView tempmapview1;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        client = new OkHttpClient();
        map=(RelativeLayout)findViewById(R.id.map);
        searchlistview=(LinearLayout)findViewById(R.id.searchlistview);


        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = auth.getCurrentUser();
        ref = db.getReference("member").child(user.getUid()+"/checkin");


        // 액션바 디자인
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0); // 그림자 없애기
        actionBar.setCustomView(R.layout.hometitle);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

         mapview1 = (ImageView)findViewById(R.id.mapone);
         mapview2= (ImageView)findViewById(R.id.maptwo);
         mapview3= (ImageView)findViewById(R.id.mapthree);
         mapview4= (ImageView)findViewById(R.id.mapfour);
         mapview5= (ImageView)findViewById(R.id.mapfive);
         mapview6= (ImageView)findViewById(R.id.mapsix);
         mapview7= (ImageView)findViewById(R.id.mapseven);
         mapview8= (ImageView)findViewById(R.id.mapeight);
         mapview9= (ImageView)findViewById(R.id.mapnine);
         mapview10= (ImageView)findViewById(R.id.mapten);
         mapview11= (ImageView)findViewById(R.id.mapeleven);
         mapview12= (ImageView)findViewById(R.id.mapriver);


        Glide.with(this).load(R.drawable.map_one_clear).into(mapview1);
        Glide.with(this).load(R.drawable.map_two_clear).into(mapview2);
        Glide.with(this).load(R.drawable.map_three_clear).into(mapview3);
        Glide.with(this).load(R.drawable.map_four_clear).into(mapview4);
        Glide.with(this).load(R.drawable.map_five_clear).into(mapview5);
        Glide.with(this).load(R.drawable.map_six_clear).into(mapview6);
        Glide.with(this).load(R.drawable.map_seven_clear).into(mapview7);
        Glide.with(this).load(R.drawable.map_eight_clear).into(mapview8);
        Glide.with(this).load(R.drawable.map_nine_clear).into(mapview9);
        Glide.with(this).load(R.drawable.map_ten_clear).into(mapview10);
        Glide.with(this).load(R.drawable.map_eleven_clear).into(mapview11);
        Glide.with(this).load(R.drawable.map).into(mapview12);

    //6 유적지 5 랜드마크  4 전통시장 3  공원 2 문화 1 쇼핑
        final ImageView imageView6 =(ImageView)findViewById(R.id.imageView6);
        final ImageView imageView5 = (ImageView)findViewById(R.id.imageView5);
        final ImageView imageView4 = (ImageView)findViewById(R.id.imageView4);
        final ImageView imageView3 = (ImageView)findViewById(R.id.imageView3);
        final ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);

        Glide.with(this).load(R.drawable.history).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView6){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView6.setImageDrawable(circularBitmapDrawable);
            }
        });

        Glide.with(this).load(R.drawable.landmark).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView5){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView5.setImageDrawable(circularBitmapDrawable);
            }
        });

        Glide.with(this).load(R.drawable.markets).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView4){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView4.setImageDrawable(circularBitmapDrawable);
            }
        });

        Glide.with(this).load(R.drawable.parks).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView3){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView3.setImageDrawable(circularBitmapDrawable);
            }
        });

        Glide.with(this).load(R.drawable.cultures).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView2){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView2.setImageDrawable(circularBitmapDrawable);
            }
        });

        Glide.with(this).load(R.drawable.shoping).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });

        //searchInit(); //서치리스트 초기화
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"NotoSans-Regular.ttf"));

        firstListGetData getData = new firstListGetData();
        String result = null;
        try {
            result = getData.execute().get();
            JSONObject object = new JSONObject(result);
            JSONArray jsonArray = object.getJSONArray("location");
            listInit(jsonArray);
            searchInit(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        //listInit(); //리스트 초기화



        mLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG,"onPanelSlide, offset "+slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG,"onPanelStateChanged" + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        mapview1.setOnClickListener(new guListner("1"));
        mapview2.setOnClickListener(new guListner("2"));
        mapview3.setOnClickListener(new guListner("3"));
        mapview4.setOnClickListener(new guListner("4"));
        mapview5.setOnClickListener(new guListner("5"));
        mapview6.setOnClickListener(new guListner("6"));
        mapview7.setOnClickListener(new guListner("7"));
        mapview8.setOnClickListener(new guListner("8"));
        mapview9.setOnClickListener(new guListner("9"));
        mapview10.setOnClickListener(new guListner("10"));
        mapview11.setOnClickListener(new guListner("11"));

        imageView6.setOnClickListener(new MyListner("6"));
        imageView5.setOnClickListener(new MyListner("5"));
        imageView4.setOnClickListener(new MyListner("4"));
        imageView3.setOnClickListener(new MyListner("3"));
        imageView2.setOnClickListener(new MyListner("2"));
        imageView.setOnClickListener(new MyListner("1"));

        //체크인 기록 불러오기
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String s) {
                if(data.child("guNumber").getValue()!=null)
                {
                    switch (Integer.parseInt(""+data.child("guNumber").getValue())){
                        case 1 :
                            guNumber_1++;
                            break;
                        case 2 :
                            guNumber_2++;
                            break;
                        case 3 :
                            guNumber_3++;
                            break;
                        case 4 :
                            guNumber_4++;
                            break;
                        case 5 :
                            guNumber_5++;
                            break;
                        case 6 :
                            guNumber_6++;
                            break;
                        case 7 :
                            guNumber_7++;
                            break;
                        case 8 :
                            guNumber_8++;
                            break;
                        case 9 :
                            guNumber_9++;
                            break;
                        case 10 :
                            guNumber_10++;
                            break;
                        case 11 :
                            guNumber_11++;
                            break;

                        default: break;
                    }
                }
                Log.d("영등포구 체크인 수: ", ""+guNumber_10);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


        gustage();
    }
//1.은평 1   ,  2.마포 4   3.종로 21   4.성북 1  5.강북 1    6. 중랑0    7.송파 2   8. 서초3   9.관악 0  10.영등포  5,  11.강서 0
    public void gustage(){
       guone();
        gutwo();
        guthree();
        gufour();
        gufive();
        gusix();
        guseven();
        gueight();
        gunine();
        guten();
        gueleven();
    }

    public void gueleven(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_11/1*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }
    public void guten(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_10/5*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }

    public void gunine(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_9/1*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }

    public void gueight(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_8/3*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }
    public void guseven(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_7/2*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }
    public void gusix(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_6/1*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }

    public void gufive(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_5/1*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }

    public void gufour(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_4/1*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }

    public void guone(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_1/1*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }

    public void gutwo(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_2/4*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }

    public void guthree(){
        int per = 100;
        int i;
        int tempper ;
        for(i=5;i<=1;i--){
            tempper = per/i;
            if(guNumber_3/21*100<=tempper){
                break;
            }
        }
        switch (per/i){
            case 20 :
                break;

            case 40 :
                break;

            case 60 :
                break;

            case 80 :
                break;

            case 100 :
                break;
        }
    }


    class guListner implements View.OnClickListener{
        String guNum;
        public guListner(String guNum){
            this.guNum=guNum;
        }
        @Override
        public void onClick(View v) {
            client = new OkHttpClient();
            guListGetData getData = new guListGetData();
            String result = null;
            try{
                result = getData.execute(guNum).get();
                jsonobject = new JSONObject(result);
                jsonarray = jsonobject.getJSONArray("location");
                listInit(jsonarray);
            }catch (Exception e){
                e.printStackTrace();
            }
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
    }


    class MyListner implements View.OnClickListener{
        String categoryNum;
        public MyListner(String categoryNum) {
            this.categoryNum = categoryNum;
        }
        @Override
        public void onClick(View v) {
            client = new OkHttpClient();
            categoryListGetData getData = new categoryListGetData();
            String result = null;
            try{
                result = getData.execute(categoryNum).get();
                jsonobject = new JSONObject(result);
                jsonarray = jsonobject.getJSONArray("location");
                listInit(jsonarray);
            }catch (Exception e){
                e.printStackTrace();
            }
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);


        }
    }

    public void listInit(JSONArray listarr){
        lv =(ListView) findViewById(R.id.list);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Log.d("list",listarr.toString());
        ArrayList<Listviewitem> data = new ArrayList<>();

        for(int i=0;i<listarr.length();i++){
            try{
            JSONObject object = listarr.getJSONObject(i);
             Listviewitem tempdata = new Listviewitem(object.getString("loca_photo"),object.getString("loca_name"),object.getString("loca_checkincount"),object.getString("loca_reviewcount"),object.getString("loca_categorynum"),object.getString("loca_guNum"),R.drawable.heart);
             data.add(tempdata);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        Log.d("list","data.size -> "+data.size());
        adapter = new ListviewAdapter(this,R.layout.item,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = (String)adapter.getItem(position);
                //Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show(); //옮기고 이거써
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("name",str);
                startActivity(intent);
            }
        });
    }

    public void listInit(){
        lv =(ListView) findViewById(R.id.list);

        ArrayList<Listviewitem> data = new ArrayList<>();
        String title;

     /*   Listviewitem data1 = new Listviewitem(R.drawable.ic_launcher,"one","ones","oness",R.drawable.heart1);
        Listviewitem data2 = new Listviewitem(R.drawable.ic_launcher,"two","twos","twoss",R.drawable.heart1);
        Listviewitem data3 = new Listviewitem(R.drawable.ic_launcher,"three","threes","thress",R.drawable.heart1);
        Listviewitem data4 = new Listviewitem(R.drawable.ic_launcher,"four","fours","fourss",R.drawable.heart1);
        Listviewitem data5 = new Listviewitem(R.drawable.ic_launcher,"five","fives","fivess",R.drawable.heart1);
        Listviewitem data6 = new Listviewitem(R.drawable.ic_launcher,"six","sixs","sixss",R.drawable.heart1);
        Listviewitem data7 = new Listviewitem(R.drawable.ic_launcher,"seven","sevens","sevenss",R.drawable.heart1);
        Listviewitem data8 = new Listviewitem(R.drawable.ic_launcher,"eight","eights","eightss",R.drawable.heart1);
        Listviewitem data9 = new Listviewitem(R.drawable.ic_launcher,"nine","nines","niness",R.drawable.heart1);
        Listviewitem data10 = new Listviewitem(R.drawable.ic_launcher,"ten","tens","tenss",R.drawable.heart1);
        Listviewitem data11 = new Listviewitem(R.drawable.ic_launcher,"eleven","elevens","envenss",R.drawable.heart1);

        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        data.add(data6);
        data.add(data7);
        data.add(data8);
        data.add(data9);
        data.add(data10);
        data.add(data11);
*/
         adapter = new ListviewAdapter(this,R.layout.item,data);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = (String)adapter.getItem(position);
                //Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show(); //옮기고 이거써
                Toast.makeText(home.this,str,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void searchInit(JSONArray array){
        listView1 = (ListView)findViewById(R.id.listview1);
        searchdatas = new ArrayList<>(); //장소일때 주소일때 0,1 구별해서 state 넣기

        for(int i=0;i<array.length();i++){
            try{
                JSONObject object = array.getJSONObject(i);
                Searchitem tempdata = new Searchitem(object.getString("loca_name"),R.drawable.pointer_small,0);
                searchdatas.add(tempdata);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        Searchitem tempdata1  = new Searchitem("은평구",R.drawable.white,1);
        Searchitem tempdata2  = new Searchitem("서대문구",R.drawable.white,1);
        Searchitem tempdata3  = new Searchitem("마포구",R.drawable.white,1);
        Searchitem tempdata4  = new Searchitem("중구",R.drawable.white,1);
        Searchitem tempdata5  = new Searchitem("성북구",R.drawable.white,1);
        Searchitem tempdata6  = new Searchitem("동대문구",R.drawable.white,1);
        Searchitem tempdata7  = new Searchitem("성동구",R.drawable.white,1);
        Searchitem tempdata8  = new Searchitem("강북구",R.drawable.white,1);
        Searchitem tempdata9  = new Searchitem("도봉구",R.drawable.white,1);
        Searchitem tempdata10  = new Searchitem("노원구",R.drawable.white,1);
        Searchitem tempdata11  = new Searchitem("중랑구",R.drawable.white,1);
        Searchitem tempdata12  = new Searchitem("광진구",R.drawable.white,1);
        Searchitem tempdata13  = new Searchitem("송파구",R.drawable.white,1);
        Searchitem tempdata14  = new Searchitem("강동구",R.drawable.white,1);
        Searchitem tempdata15  = new Searchitem("서초구",R.drawable.white,1);
        Searchitem tempdata16  = new Searchitem("강남구",R.drawable.white,1);
        Searchitem tempdata17  = new Searchitem("관악구",R.drawable.white,1);
        Searchitem tempdata18  = new Searchitem("금천구",R.drawable.white,1);
        Searchitem tempdata19  = new Searchitem("영등포구",R.drawable.white,1);
        Searchitem tempdata20  = new Searchitem("동작구",R.drawable.white,1);
        Searchitem tempdata21  = new Searchitem("강서구",R.drawable.white,1);
        Searchitem tempdata22  = new Searchitem("양천구",R.drawable.white,1);
        Searchitem tempdata23  = new Searchitem("구로구",R.drawable.white,1);
        Searchitem tempdata24  = new Searchitem("용산구",R.drawable.white,1);
        Searchitem tempdata25  = new Searchitem("종로구",R.drawable.white,1);
        searchdatas.add(tempdata1);
        searchdatas.add(tempdata2);
        searchdatas.add(tempdata3);
        searchdatas.add(tempdata4);
        searchdatas.add(tempdata5);
        searchdatas.add(tempdata6);
        searchdatas.add(tempdata7);
        searchdatas.add(tempdata8);
        searchdatas.add(tempdata9);
        searchdatas.add(tempdata10);
        searchdatas.add(tempdata11);
        searchdatas.add(tempdata12);
        searchdatas.add(tempdata13);
        searchdatas.add(tempdata14);
        searchdatas.add(tempdata15);
        searchdatas.add(tempdata16);
        searchdatas.add(tempdata17);
        searchdatas.add(tempdata18);
        searchdatas.add(tempdata19);
        searchdatas.add(tempdata20);
        searchdatas.add(tempdata21);
        searchdatas.add(tempdata22);
        searchdatas.add(tempdata23);
        searchdatas.add(tempdata24);
        searchdatas.add(tempdata25);



        sadapter = new SearchitemAdapter(this,R.layout.searchitem,searchdatas);
        listView1.setAdapter(sadapter);
        listView1.setDivider(null);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("text","listview1clicked");
                String str = (String)sadapter.getItem(position);
                String tt[] = str.split(":");
                Log.d("list","tt[1]-->"+tt[1]);
                if(tt[1].equals("0")){
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    intent.putExtra("name",tt[0]);
                    startActivity(intent);
                }else{
                    searchListGetData searchListGetData = new searchListGetData();
                    String result = null;

                    try{
                        result = searchListGetData.execute(tt[0]).get();
                        jsonobject = new JSONObject(result);
                        jsonarray = jsonobject.getJSONArray("location");
                        Log.d("list","jsonarraysize"+jsonarray.length());
                        listInit(jsonarray);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                }
            }
        });
    }

    public void searchInit(){
        listView1 = (ListView)findViewById(R.id.listview1);
        searchdatas = new ArrayList<>(); //장소일때 주소일때 0,1 구별해서 state 넣기


      /*  Searchitem searchdata1 = new Searchitem("dog",R.drawable.heart2,0);
        Searchitem searchdata2 = new Searchitem("eagle",R.drawable.heart2,0);
        Searchitem searchdata3 = new Searchitem("pig",R.drawable.heart2,1);
        Searchitem searchdata4 = new Searchitem("tiger",R.drawable.heart2,1);
        Searchitem searchdata5 = new Searchitem("lion",R.drawable.heart2,0);
        Searchitem searchdata6 = new Searchitem("bird",R.drawable.heart2,1);
        Searchitem searchdata7 = new Searchitem("human",R.drawable.heart2,0);
        Searchitem searchdata8 = new Searchitem("ant",R.drawable.heart2,1);
        Searchitem searchdata9 = new Searchitem("kotaekwang",R.drawable.heart2,0);
        Searchitem searchdata10 = new Searchitem("baek",R.drawable.heart2,1);
        Searchitem searchdata11 = new Searchitem("seo",R.drawable.heart2,0);
        Searchitem searchdata12 = new Searchitem("han",R.drawable.heart2,1);

        searchdatas.add(searchdata1);
        searchdatas.add(searchdata2);
        searchdatas.add(searchdata3);
        searchdatas.add(searchdata4);
        searchdatas.add(searchdata5);
        searchdatas.add(searchdata6);
        searchdatas.add(searchdata7);
        searchdatas.add(searchdata8);
        searchdatas.add(searchdata9);
        searchdatas.add(searchdata10);
        searchdatas.add(searchdata11);
        searchdatas.add(searchdata12);*/

        sadapter = new SearchitemAdapter(this,R.layout.searchitem,searchdatas);
        listView1.setAdapter(sadapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("text","listview1clicked");
                String str = (String)sadapter.getItem(position);
                //Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show(); //옮기고 이거써
                Toast.makeText(home.this,str,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) { //펼쳐있을때 back 누르면 닫히는거
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    private Drawable resizeImage(int resId,int w,int h){
        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),resId);
        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();

        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float)newWidth)/width;
        float scaleHeight =  ((float)newHeight)/height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg,0,0,width,height,matrix,true);
        return new BitmapDrawable(resizedBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //search
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_view,menu);
        MenuItem mSearch = menu.findItem(R.id.search);

        mSearch.setIcon(resizeImage(R.drawable.search,150,150));
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        MenuItemCompat.setOnActionExpandListener(mSearch, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {  //서치 아이콘 누를때  expand
                Log.d("text","open");
                map.setVisibility(View.INVISIBLE); // map화면 가려짐
                searchlistview.setVisibility(View.VISIBLE); //searchlist 화면 켜짐
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) { //서치 끌때
                Log.d("text","close");
                searchlistview.setVisibility(View.INVISIBLE);
                map.setVisibility(View.VISIBLE);
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                return true;
            }


        });

        searchView  = (SearchView) MenuItemCompat.getActionView(mSearch);

        searchView.setSubmitButtonEnabled(true);
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Log.d("text",query); -> query 찍힘
                searchListGetData searchListGetData = new searchListGetData();
                String result = null;

                try{
                    result = searchListGetData.execute(query).get();
                    jsonobject = new JSONObject(result);
                    jsonarray = jsonobject.getJSONArray("location");
                    Log.d("list","jsonarraysize"+jsonarray.length());
                    listInit(jsonarray);
                }catch (Exception e){
                    e.printStackTrace();
                }

                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!=null){
                sadapter.filter(newText);
                Log.d("text",""+newText);
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);}
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }

    public class firstListGetData extends AsyncTask<String, Void, String>{
        String listResult;

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/showloca")
                    .build();

            try{
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public class searchListGetData extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            try {
                json.put("searchtext", params[0]);
            }catch(Exception e){
                e.printStackTrace();
            }
            RequestBody posData = RequestBody.create(JSON,json.toString());
            request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/searchLocaAddress")
                    .post(posData)
                    .build();
            try{
                response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            //post gu num
            return null;
        }
    }

    public class guListGetData extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            RequestBody posData = new FormBody.Builder()
                    .add("type","json")
                    .add("guNum",params[0])
                    .build();

            request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/showGuLoca")
                    .post(posData)
                    .build();
            try{
                response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            //post gu num
            return null;
        }
    }

    public class categoryListGetData extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            RequestBody posData = new FormBody.Builder()
                    .add("type","json")
                    .add("categoryNum",params[0])
                    .build();

            request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/showCategoryLoca")
                    .post(posData)
                    .build();
            try{
                response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            //post gu num
            return null;
        }
    }




}

