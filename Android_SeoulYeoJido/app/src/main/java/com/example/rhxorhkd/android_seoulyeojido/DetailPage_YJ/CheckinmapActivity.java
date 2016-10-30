package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CheckinmapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    OkHttpClient client = new OkHttpClient();
    JSONObject jsonobject;
    JSONArray jsonarray;
    Response response;
    Request request;

    JSONArray jsonArray;
    private GoogleMap mMap;
    private GpsInfo gps;

    private LocationManager locationManager = null;
    private Double longitude;
    private Double latitude;


    private RelativeLayout rl;
    private TextView tv1;
    private String markerId;

    private long now;
    private Date date;
    private String time;
    private String guNumber;
    private String lat;
    private String lon;
    private String showphoto =null;
    private String showtitle=null;
    private String showcheckincount=null;
    private String showreviewcount=null;
    private String showcategorygu=null;

    private TextView showtitletext ;
    private TextView showcheckincounttext;
    private TextView showreviewcounttext;
    private TextView showcategorygutext;
    private ImageView showphotoimage;

    public class firstListGetData extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkinmap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        showtitletext =(TextView)findViewById(R.id.location_name);
        Log.d("list","categorygu : "+showcategorygu);
        showcategorygutext = (TextView)findViewById(R.id.checkin_categu);
        showcheckincounttext = (TextView)findViewById(R.id.checkin_cnt);
        showreviewcounttext = (TextView)findViewById(R.id.reply_cnt);
        showphotoimage = (ImageView)findViewById(R.id.map_bottom_img);
        //글꼴 라이브러리
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "NotoSans-Regular.ttf"));

        rl = (RelativeLayout) findViewById(R.id.location_detail);
        rl.setVisibility(View.GONE);
        tv1 = (TextView) findViewById(R.id.location_name);


        gps = new GpsInfo(CheckinmapActivity.this);

        latitude = gps.getLatitude();
        longitude = gps.getLongitude();

        //checkinListGetData getData = new checkinListGetData();

        // 시스템으로부터 현재시간(ms) 가져오기
        now = System.currentTimeMillis();
        // Data 객체에 시간을 저장한다.
        date = new Date(now);

        time = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
        Toast.makeText(this, time, Toast.LENGTH_SHORT).show();

        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); //gps 사용 유/무 파악하려고
        if(!locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){ //gps 꺼져있을 시 앱이 수행할 코드
            Log.d("list","gps 연결 X");
            new AlertDialog.Builder(CheckinmapActivity.this)
                    .setMessage("GPS가 꺼져있습니다. \n 'Google 위치 서비스' 를 체크해주세요")
                    .setPositiveButton("설정", new DialogInterface.OnClickListener() { //설정 버튼 누를때
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS); //설정페이지 이동
                            startActivity(intent);
                        }
                    }).setNegativeButton("취소",null).show();

        }

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
        if (wifi.isConnected() || mobile.isConnected()) {
            // Log.i("연결됨" , "연결이 되었습니다.);
            //         setContentView(R.layout.activity_logo);
        } else {
            new AlertDialog.Builder(CheckinmapActivity.this)
                    .setMessage("인터넷 연결을 체크해주세요")
                    .setPositiveButton("설정", new DialogInterface.OnClickListener() { //설정 버튼 누를때
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS); //설정페이지 이동
                            startActivity(intent);
                        }
                    }).setNegativeButton("취소",null).show();

            //Log.i("연결 안 됨" , "연결이 다시 한번 확인해주세요);
        }


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        int height = 126;
        int width = 90;
        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.drawable.after_check_in);
        BitmapDrawable bitmapdraw2 = (BitmapDrawable) getResources().getDrawable(R.drawable.before_check_in);
        Bitmap b = bitmapdraw1.getBitmap();
        Bitmap noCheck = bitmapdraw2.getBitmap();

        Bitmap noChecksmallMarker = Bitmap.createScaledBitmap(noCheck, width, height, false);
        Bitmap CheckedsmallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng myLocation = new LatLng(latitude, longitude);
        Log.d("mr", "la: " + latitude);
        Log.d("mr", "lo: " + longitude);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15));
//        mMap.addMarker(new MarkerOptions()
//                .title("현재 위치")
//                .snippet("innoaus.")
//                .position(myLocation));

        mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(500)
                .strokeColor(Color.parseColor("#884169e1"))
                .fillColor(Color.parseColor("#5587cefa")));


        firstListGetData getData = new firstListGetData();
        String result = null;
        try {
            result = getData.execute().get();
            JSONObject object = new JSONObject(result);
            jsonArray = object.getJSONArray("location");
        }catch (Exception e){
            e.printStackTrace();
        }


        if(jsonArray!=null){
            for(int i=0;i<jsonArray.length();i++) {
                try{
                JSONObject object = jsonArray.getJSONObject(i);
                String lat = object.getString("loca_lat");
                String lon = object.getString("loca_lon");
                String title = object.getString("loca_name");
                 LatLng temp = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
                mMap.addMarker(new MarkerOptions().position(temp).title(title).icon(BitmapDescriptorFactory.fromBitmap(CheckedsmallMarker)));

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                tv1.setText(marker.getTitle());
                showlocainfo(marker.getTitle());
                showcheckincounttext.setText(showcheckincount);
                showcategorygutext.setText(showcategorygu);
                showreviewcounttext.setText(showreviewcount);
                showtitletext.setText(showtitle);
                Glide.with(rl.getContext()).load(showphoto).into(showphotoimage);

                rl.setVisibility(View.VISIBLE);

                markerId = marker.getId().toString();

                String results =null;
                Log.d("list","showtitle : "+showtitle);
                try{
                    checkinsuccess checkinsuccess = new checkinsuccess();
                    results = checkinsuccess.execute(showtitle,latitude.toString(),longitude.toString()).get();
                    Log.d("list","result : "+results);
                }catch (Exception e){
                    e.printStackTrace();
                }

                Intent intentSubActivity = new Intent(CheckinmapActivity.this, CheckinPopup.class);
                intentSubActivity.putExtra("position", marker.getPosition());
                intentSubActivity.putExtra("title", marker.getTitle());
                intentSubActivity.putExtra("time", time);
                intentSubActivity.putExtra("result", results);

                startActivity(intentSubActivity);
                return false;
            }
        });

        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("name",showtitle);
                startActivity(intent);
            }
        });
    }


    public class checkinsuccess extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            try {
                json.put("loca_name", params[0]);
                json.put("loca_lat",params[1]);
                json.put("loca_lon",params[2]);
            }catch(Exception e){
                e.printStackTrace();
            }
            RequestBody posData = RequestBody.create(JSON,json.toString());
            request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/checkin")
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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
//            case R.id.default_flag :
//                break;

            default: break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    public void showlocainfo(String title){
        showtitle=title;
        if(jsonArray!=null){
            for(int i=0;i<jsonArray.length();i++) {
                try{
                    JSONObject object = jsonArray.getJSONObject(i);
                    String temptitle = object.getString("loca_name");
                    if(temptitle.equals(showtitle)){
                        showphoto = object.getString("loca_photo");
                        showcheckincount = object.getString("loca_checkincount");
                        showreviewcount =object.getString("loca_reviewcount");
                        showcategorygu = object.getString("loca_categorynum")+"|"+object.getString("loca_guNum");
                         break;
                    }else continue;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
