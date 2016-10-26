package com.example.rhxorhkd.android_seoulyeojido;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ.DetailActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class home extends AppCompatActivity {
    ListviewAdapter adapter; //listview adapter
    SearchitemAdapter sadapter; //search adapter
    LinearLayout map; //지도
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public void button2clicked(View v){ //용산구
        guListGetData getData = new guListGetData();
        String result = null;
        try{
            result = getData.execute("2").get();
            jsonobject = new JSONObject(result);
            jsonarray = jsonobject.getJSONArray("location");
            listInit(jsonarray);
        }catch (Exception e){
            e.printStackTrace();
        }

        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        client = new OkHttpClient();
        map=(LinearLayout)findViewById(R.id.map);
        searchlistview=(LinearLayout)findViewById(R.id.searchlistview);
        button =(Button) findViewById(R.id.button);
        ImageView imageView6 =(ImageView)findViewById(R.id.imageView6);
        ImageView imageView5 = (ImageView)findViewById(R.id.imageView5);

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

        imageView5.setOnClickListener(new MyListner());
    }

    class MyListner implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            client = new OkHttpClient();
            categoryListGetData getData = new categoryListGetData();
            String result = null;
            try{
                result = getData.execute("2").get();
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
        Log.d("list",listarr.toString());
        ArrayList<Listviewitem> data = new ArrayList<>();

        for(int i=0;i<listarr.length();i++){
            try{
            JSONObject object = listarr.getJSONObject(i);
             Listviewitem tempdata = new Listviewitem(object.getString("loca_photo"),object.getString("loca_name"),object.getString("loca_categorynum"),object.getString("loca_guNum"),R.drawable.heart1);
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

                Toast.makeText(home.this,str,Toast.LENGTH_LONG).show();
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
                Searchitem tempdata = new Searchitem(object.getString("loca_name"),R.drawable.heart2,0);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //search
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_view,menu);
        MenuItem mSearch = menu.findItem(R.id.search);
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
                sadapter.filter(newText);
                Log.d("text",""+newText);
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
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

