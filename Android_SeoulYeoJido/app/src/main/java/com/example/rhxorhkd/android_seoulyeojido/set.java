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
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.AnalysisFragment;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.BookmarkFragment;
import com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment.VisitedFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class set extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase db;
    private DatabaseReference ref;


    private ImageView iv, iv2;
    private TextView tv, tv2;

    private static String total_checkin ="0";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("member");


//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("아이린");

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl_tab);
        ViewPager viewPager = (ViewPager)findViewById(R.id.vp_pager);

        Fragment[] fragments = new Fragment[3];
        fragments[0] = new AnalysisFragment();
        fragments[1] = new VisitedFragment();
        fragments[2] = new BookmarkFragment();

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
        if(i.getStringExtra("uid") == null){//탭으로 본 마이페이지
            iv2.setImageDrawable(null);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot data) {
                    String nickName = ""+data.child(user.getUid()+"/nickname").getValue();
                    tv.setText(nickName);
                    if(data.child(user.getUid()+"/nickname").getValue() != null){
                        Glide.with(set.this).load(data.child(user.getUid()+"/profile").getValue()).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv){
                            @Override
                            protected void setResource(Bitmap resource) {
                                super.setResource(resource);
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(this.getView().getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                iv.setImageDrawable(circularBitmapDrawable);
                            }
                        });
                    }else{
                        Glide.with(set.this).load(R.drawable.profile).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv){
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
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else{//랭킹에서 넘어온경우
            findViewById(R.id.back_btn).setOnClickListener(this);
            tv.setText(i.getStringExtra("name"));
//            total_checkin = i.getStringExtra("cnt");

            Glide.with(this).load(i.getStringExtra("profile")).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv){
                @Override
                protected void setResource(Bitmap resource) {
                    super.setResource(resource);
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(this.getView().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv.setImageDrawable(circularBitmapDrawable);
                }
            });

            ref.child(i.getStringExtra("uid")).child("checkin").addChildEventListener(new ChildEventListener() {
                int guNumber_1, guNumber_2, guNumber_3, guNumber_4, guNumber_5, guNumber_6, guNumber_7,
                        guNumber_8, guNumber_9, guNumber_10, guNumber_11;
                int cnt = 0;

                @Override
                public void onChildAdded(DataSnapshot data, String s) {
                    cnt++;


//                    if(data.child("guNumber").getValue()!=null){
//                        switch (Integer.parseInt(""+data.child("guNumber").getValue())){
//                            case 1 :
//                                guNumber_1++;
//                                break;
//                            case 2 :
//                                guNumber_2++;
//                                break;
//                            case 3 :
//                                guNumber_3++;
//                                break;
//                            case 4 :
//                                guNumber_4++;
//                                break;
//                            case 5 :
//                                guNumber_5++;
//                                break;
//                            case 6 :
//                                guNumber_6++;
//                                break;
//                            case 7 :
//                                guNumber_7++;
//                                break;
//                            case 8 :
//                                guNumber_8++;
//                                break;
//                            case 9 :
//                                guNumber_9++;
//                                break;
//                            case 10 :
//                                guNumber_10++;
//                                break;
//                            case 11 :
//                                guNumber_11++;
//                                break;
//
//                            default: break;
//                        }
//
//                    }
                    if(cnt == data.getChildrenCount()){
//                        total_checkin = ""+data.getChildrenCount();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

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
                    return  "나의 서울";
                case 1 :
                    return "체크인";
                case 2 :
                    return "담은 서울";
                default:
                    return "";
            }
        }
    }
}
