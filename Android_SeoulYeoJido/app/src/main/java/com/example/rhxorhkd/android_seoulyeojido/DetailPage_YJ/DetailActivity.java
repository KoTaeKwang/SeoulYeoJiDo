package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.R;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView textView;
    private RecyclerView lecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 삭제
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        final String locationTitle = intent.getStringExtra("name");
//        textView = (TextView) findViewById(R.id.text1);
//        textView.setText(locationTitle);


        loadBackdrop();     //이미지 로드

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(locationTitle);



        initLayout();       //댓글 초기화
        initData();         //댓글

        /**
         * 댓글 입력
         */
        Button btnReview = (Button)findViewById(R.id.btn_review);
        btnReview.setOnClickListener(mClickListener);

        /**
         * 전체 댓글보기
         */
        Button btnMore = (Button)findViewById(R.id.btn_more);
        btnMore.setOnClickListener(mClickListener);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello World", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_review:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(DetailActivity.this, Dialog.class);
                    startActivity(intentSubActivity);
                    break;

                case R.id.btn_more:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity2 =
                            new Intent(DetailActivity.this, AllreviewActivity.class);
                    startActivity(intentSubActivity2);
                    break;
            }
        }
    };


    /**
     * 액션바 뒤로가기
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void initLayout(){

        lecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }

    /**
     * 데이터 초기화
     */
    private void initData(){

        List<DetailReview> reviewList = new ArrayList<>();

        for (int i =0; i<3; i ++){

            DetailReview review = new DetailReview();

            review.setImage(R.drawable.irene3);
            review.setTitle("사용자명 "+ (i+1));
            review.setArtist("리뷰리뷰 써주세요 "+ (i+1));
            reviewList.add(review);
        }

        lecyclerView.setAdapter(new DetailRecyclerAdapter(reviewList,R.layout.detail_row));
        lecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        lecyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    /**
     * 이미지 로드
     */
    private void loadBackdrop() {
        final ImageView imageview = (ImageView) findViewById(R.id.backdrop);

        //Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
        Glide.with(this).load("http://kinimage.naver.net/20161005_33/1475666020537jIagj_JPEG/1475666020386.jpg?type=w620").into(imageview);

    }

}
