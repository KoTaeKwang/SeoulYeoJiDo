package com.example.rhxorhkd.android_seoulyeojido;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.AnalysisFragment;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.BookmarkFragment;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.VisitedFragment;

public class set extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv, iv2;
    private TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl_tab);
        ViewPager viewPager = (ViewPager)findViewById(R.id.vp_pager);

        Fragment[] fragments = new Fragment[3];
        fragments[0] = new VisitedFragment();
        fragments[1] = new BookmarkFragment();
        fragments[2] = new AnalysisFragment();

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        findViewById(R.id.gotoMap).setOnClickListener(this);
        findViewById(R.id.my_profile_img).setOnClickListener(this);

        iv = (ImageView)findViewById(R.id.my_profile_img);
        iv2 = (ImageView)findViewById(R.id.back_btn);
        tv = (TextView)findViewById(R.id.down_name);
        tv2 = (TextView)findViewById(R.id.up_name);

        Intent i = getIntent();
        if(i.getStringExtra("name") == null){
            iv2.setImageDrawable(null);

        }else{
            findViewById(R.id.back_btn).setOnClickListener(this);
        }
//        tv.setText(i.getStringExtra("name"));
        tv.setText("아이린");


        Glide.with(this).load(R.drawable.irene1).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(this.getView().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iv.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static int REQ_CODE_SELECT_IMAGE = 100;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_btn :
                finish();
                break;
            case R.id.gotoMap :
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.my_profile_img :
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ_CODE_SELECT_IMAGE);
                break;
            default: break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Glide.with(this).load(data.getData()).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(this.getView().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments;

        public MyPagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 :
                    return  "다녀온 곳";
                case 1 :
                    return "갈 곳";
                case 2 :
                    return "분석";
                default:
                    return "";
            }
        }
    }
}
