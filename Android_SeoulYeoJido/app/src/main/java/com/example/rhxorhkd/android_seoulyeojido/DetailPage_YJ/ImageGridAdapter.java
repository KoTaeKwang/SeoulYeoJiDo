package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.rhxorhkd.android_seoulyeojido.R;

/**
 * Created by hanyoojin on 2016. 10. 25..
 */

public class ImageGridAdapter extends BaseAdapter {
    Context context;

    public Integer[] mThumblds ={
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4,
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4,
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4,
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4,
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4,
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4,
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4,
            R.drawable.cheese_4, R.drawable.cheese_4, R.drawable.cheese_4
    };

    public ImageGridAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return mThumblds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300,300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else{
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumblds[position]);
        return imageView;
    }
}

