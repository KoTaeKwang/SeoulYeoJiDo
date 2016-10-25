package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.rhxorhkd.android_seoulyeojido.R;

import java.util.ArrayList;
import java.util.List;

public class ImageViewPager extends AppCompatActivity {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_pager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent p = getIntent();
        position = p.getExtras().getInt("id");

        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(this);
        List<ImageView> images = new ArrayList<ImageView>();

        for(int i=0; i<imageGridAdapter.getCount(); i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageGridAdapter.mThumblds[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setScaleType(ImageView.ScaleType.CENTER);
            images.add(imageView);
        }

        ImagePagerAdapter pageradapter = new ImagePagerAdapter(images);
        ViewPager viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(pageradapter);

        viewpager.setCurrentItem(position);

    }

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

}
