package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.R;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by YJ on 2016-10-25.
 */

public class DetailFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.detail_fragment, container, false);
        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        /**
         * 이미지버튼- 전화걸기
         */
        ImageButton imgbtn =  (ImageButton)view.findViewById(R.id.img_frg1);
        TextView txt_tel = (TextView)view.findViewById(R.id.txt_tel);
        final String tel = txt_tel.getText().toString();

        imgbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
                startActivity(intent);
            }
        });


        ImageView imageView = (ImageView)view.findViewById(R.id.img_photo1);
        Glide.with(this).load("http://cfile6.uf.tistory.com/image/2339F739567D2CAE3CADF4").into(imageView);




    }


}
