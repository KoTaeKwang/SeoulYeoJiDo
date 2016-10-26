package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import com.example.rhxorhkd.android_seoulyeojido.MapsActivity;
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
        ImageView imgTel =  (ImageView) view.findViewById(R.id.img_frg1);
        ImageView imgMap =  (ImageView) view.findViewById(R.id.img_frg2);
        ImageView imgURL = (ImageView) view.findViewById(R.id.img_frg3);

        TextView txt_tel = (TextView)view.findViewById(R.id.txt_tel);
        final String tel = txt_tel.getText().toString();

        imgTel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
                startActivity(intent);
            }
        });

        imgMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Toast.makeText(getContext(), "map 연결",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), DetailMapsActivity.class));
            }
        });



        imgURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                startActivity(intent);

            }
        });

        ImageView imageView1 = (ImageView)view.findViewById(R.id.img_photo1);
        ImageView imageView2 = (ImageView)view.findViewById(R.id.img_photo2);
        ImageView imageView3 = (ImageView)view.findViewById(R.id.img_photo3);
        ImageView imageView4 = (ImageView)view.findViewById(R.id.img_photo4);
        Glide.with(this).load("http://www.natuur-pop.com/_upload/icecream/2013871578_1293.jpg").into(imageView1);
        Glide.with(this).load("http://www.natuur-pop.com/_upload/icecream/201387229_35063.jpg").into(imageView2);
        Glide.with(this).load("http://www.natuur-pop.com/_upload/icecream/2013871422_15767.jpg").into(imageView3);
        Glide.with(this).load("http://www.natuur-pop.com/_upload/icecream/2013872115_26090.jpg").into(imageView4);

        imageView4.setBackgroundColor(new Color().argb(255, 40, 40, 40));
        imageView4.setAlpha(50);

        imageView4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ImageGridActivity.class);
                startActivity(intent);
            }
        });
    }

}
