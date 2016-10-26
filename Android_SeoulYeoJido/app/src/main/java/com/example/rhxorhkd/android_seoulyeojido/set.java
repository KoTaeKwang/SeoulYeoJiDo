package com.example.rhxorhkd.android_seoulyeojido;

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

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.AnalysisFragment;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.BookmarkFragment;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.VisitedFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class set extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;
    private FirebaseUser user;


    private ImageView iv, iv2;
    private TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("아이린");

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
        if(i.getStringExtra("name") == null){//탭으로 본 마이페이지
            iv2.setImageDrawable(null);
            tv.setText(user.getDisplayName().toString());

            Glide.with(this).load(user.getPhotoUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv){
                @Override
                protected void setResource(Bitmap resource) {
                    super.setResource(resource);
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(this.getView().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv.setImageDrawable(circularBitmapDrawable);
                }
            });

        }else{//랭킹에서 넘어온경우
            findViewById(R.id.back_btn).setOnClickListener(this);
            tv.setText(i.getStringExtra("name"));

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



    }


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
                Intent i = new Intent(this, ChangeInfo.class);
                i.putExtra("nickname", tv.getText());
                startActivity(i);
                break;
            default: break;
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
                    return  "\n";
                case 1 :
                    return "담은 서울";
                case 2 :
                    return "나의 서울";
                default:
                    return "";
            }
        }
    }
}
