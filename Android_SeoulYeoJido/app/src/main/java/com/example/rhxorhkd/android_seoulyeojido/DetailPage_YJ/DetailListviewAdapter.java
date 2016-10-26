package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhxorhkd.android_seoulyeojido.Listviewitem;
import com.example.rhxorhkd.android_seoulyeojido.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanyoojin on 2016. 10. 26..
 */

public class DetailListviewAdapter extends BaseAdapter {


//    private List<DetailReview> reviewList;
//    private int itemLayout;

    private LayoutInflater inflater;
    private ArrayList<DetailReview> reviewList;
    private int itemLayout;

    public DetailListviewAdapter(Context context, int itemLayout, ArrayList<DetailReview> reviewList){
        this.inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.reviewList=reviewList;
        this.itemLayout=itemLayout;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return reviewList.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewList.get(position).getTitle();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(itemLayout, parent, false);
        }

        DetailReview detailReview = reviewList.get(position);

        ImageView imgProfile = (ImageView) convertView.findViewById(R.id.imgProfile);
        imgProfile.setImageResource(detailReview.getImage());

        TextView title = (TextView)convertView.findViewById(R.id.username);
        title.setText(detailReview.getTitle());

        TextView review = (TextView)convertView.findViewById(R.id.review);
        review.setText(detailReview.getTitle());

        return convertView;
       // return null;
    }


}
