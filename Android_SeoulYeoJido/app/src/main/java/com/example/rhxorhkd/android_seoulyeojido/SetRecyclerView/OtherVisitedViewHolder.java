package com.example.rhxorhkd.android_seoulyeojido.SetRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhxorhkd.android_seoulyeojido.R;

/**
 * Created by 병윤 on 2016-10-30.
 */

public class OtherVisitedViewHolder extends RecyclerView.ViewHolder {

    final View mView;
    final ImageView photo;
    final TextView name;
    final TextView cnt, cate_guName;



    public OtherVisitedViewHolder(View itemView, OtherVisitedAdapter otherVisitedAdapter) {
        super(itemView);
        mView = itemView;
        photo = (ImageView)itemView.findViewById(R.id.other_visited_img);
        name = (TextView)itemView.findViewById(R.id.other_visited_name);
        cnt = (TextView)itemView.findViewById(R.id.other_visited_cnt);
        cate_guName = (TextView)itemView.findViewById(R.id.other_visited_cate_gu);
    }
}
