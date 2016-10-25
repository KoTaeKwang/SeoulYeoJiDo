package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.R;

import java.util.ArrayList;
import java.util.List;

public class AllreviewActivity extends AppCompatActivity {

    private RecyclerView lecyclerView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allreview);

        initLayout();       //댓글 초기화
        initData();         //댓글
    }

    /**
     * 데이터 초기화
     */
    private void initData() {

        List<DetailReview> reviewList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            DetailReview review = new DetailReview();
            review.setImage(R.drawable.irene3);
            review.setTitle("사용자명 " + (i + 1));
            review.setArtist("리뷰리뷰 써주세요 " + (i + 1));
            reviewList.add(review);
        }
        lecyclerView02.setAdapter(new DetailRecyclerAdapter(reviewList, R.layout.detail_row));
        lecyclerView02.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        lecyclerView02.setItemAnimator(new DefaultItemAnimator());
    }


    private void initLayout() {

        lecyclerView02 = (RecyclerView) findViewById(R.id.recyclerView2);
    }

}


