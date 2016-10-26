package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class CheckinmapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{


    private GoogleMap mMap;
    private GpsInfo gps;

    private LocationManager locationManager = null;
    private Double longitude;
    private Double latitude;


    private RelativeLayout rl;
    private TextView tv1;
    private String markerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkinmap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        rl = (RelativeLayout) findViewById(R.id.location_detail);
        rl.setVisibility(View.GONE);
        tv1 = (TextView)findViewById(R.id.location_name);

        findViewById(R.id.map_back).setOnClickListener(this);

        gps = new GpsInfo(CheckinmapActivity.this);

        latitude = gps.getLatitude();
        longitude = gps.getLongitude();


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
        Log.d("mr", "la: "+latitude);
        Log.d("mr", "lo: "+longitude);

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13));
        mMap.addMarker(new MarkerOptions()
                .title("현재 위치")
                .snippet("innoaus.")
                .position(myLocation));

        mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(500)
                .strokeColor(Color.parseColor("#884169e1"))
                .fillColor(Color.parseColor("#5587cefa")));

        // Add a marker in Sydney and move the camera
        LatLng kb = new LatLng(37.5129622, 126.9270477);
        LatLng sd = new LatLng(37.476777, 126.981783);
        LatLng ds = new LatLng(37.565776, 126.975163);
        LatLng tg = new LatLng(37.558283, 126.978028);
        LatLng md = new LatLng(37.560829, 126.986418);

        mMap.addMarker(new MarkerOptions()
                .position(kb)
                .title("경북궁 ")
                .icon(BitmapDescriptorFactory.fromBitmap(CheckedsmallMarker))

        );
        mMap.addMarker(new MarkerOptions()
                .position(sd)
                .title("사당역 ")
                .icon(BitmapDescriptorFactory.fromBitmap(CheckedsmallMarker))

        );
        mMap.addMarker(new MarkerOptions()
                .position(ds)
                .title("덕수궁 ")
                .icon(BitmapDescriptorFactory.fromBitmap(CheckedsmallMarker))

        );
        mMap.addMarker(new MarkerOptions()
                .position(md)
                .title("명동 ")
                .icon(BitmapDescriptorFactory.fromBitmap(noChecksmallMarker))

        );
        mMap.addMarker(new MarkerOptions()
                .position(tg)
                .title("퇴계로 ")
                .icon(BitmapDescriptorFactory.fromBitmap(noChecksmallMarker))

        );

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

//                Log.d("OnClickListener", "click session button");
//                // 액티비티 실행
//                Intent intentSubActivity =
//                        new Intent(DetailMapsActivity.this, CheckinPopup.class);
//                startActivity(intentSubActivity);

                tv1.setText(marker.getTitle());
                rl.setVisibility(View.VISIBLE);

                markerId = marker.getId().toString();
                Toast.makeText(getApplicationContext(), " == "+markerId, 1).show();


                Intent intentSubActivity = new Intent(CheckinmapActivity.this, CheckinPopup.class);
                startActivity(intentSubActivity);
                return false;
            }
        });

        mMap.getUiSettings().setRotateGesturesEnabled(false);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                rl.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.map_back :
                finish();
                break;
//            case R.id.default_flag :
//                break;

            default: break;
        }

    }

}
