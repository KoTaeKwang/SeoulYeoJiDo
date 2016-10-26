package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rhxorhkd.android_seoulyeojido.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.rhxorhkd.android_seoulyeojido.R.id.map;

public class DetailMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LocationManager locationManager = null;
    private Double longitude;
    private Double latitude;

    private GpsInfo gps;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        gps = new GpsInfo(DetailMapsActivity.this);

        //Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        //startActivity(myIntent);

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

        LatLng myLocation = new LatLng(latitude, longitude);
        Log.d("mr", "la: "+latitude);
        Log.d("mr", "lo: "+longitude);

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15));
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

//        mMap.addMarker(new MarkerOptions()
//                .position(kb)
//                .title("경북궁 ")
//                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
//
//        );
        mMap.addMarker(new MarkerOptions().position(kb).title("경복궁 "));
        mMap.addMarker(new MarkerOptions().position(sd).title("사당역 "));
        mMap.addMarker(new MarkerOptions().position(ds).title("덕수궁 "));
        mMap.addMarker(new MarkerOptions().position(tg).title("퇴계로 "));
        mMap.addMarker(new MarkerOptions().position(md).title("명동 "));


    }
}
