package com.example.rhxorhkd.android_seoulyeojido;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{

    private GoogleMap mMap;
    private RelativeLayout rl;
    private ImageView iv;
    private TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        rl = (RelativeLayout) findViewById(R.id.location_detail);
        rl.setVisibility(View.GONE);
        tv1 = (TextView)findViewById(R.id.location_name);

        
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

        int height = 50;
        int width = 50;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.pin);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        // Add a marker in Sydney and move the camera
        LatLng kb = new LatLng(37.577837, 126.976869);
        LatLng sd = new LatLng(37.476777, 126.981783);
        LatLng ds = new LatLng(37.565776, 126.975163);
        LatLng tg = new LatLng(37.558283, 126.978028);
        LatLng md = new LatLng(37.560829, 126.986418);
        mMap.addMarker(new MarkerOptions()
                .position(kb)
                .title("경북궁 ")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))

        );
        mMap.addMarker(new MarkerOptions().position(sd).title("사당역 "));
        mMap.addMarker(new MarkerOptions().position(ds).title("덕수궁 "));
        mMap.addMarker(new MarkerOptions().position(tg).title("퇴계로 "));
        mMap.addMarker(new MarkerOptions().position(md).title("명동 "));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.53501414281699,126.98524095118046), 13));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                tv1.setText(marker.getTitle());

                rl.setVisibility(View.VISIBLE);
                return false;
            }
        });
        mMap.getUiSettings().setRotateGesturesEnabled(false);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("여기는!!!!", ""+latLng);
            }
        });

//        mMap.setMyLocationEnabled(true);



    }


    @Override
    public void onClick(View view) {

    }
}
