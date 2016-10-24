package com.example.rhxorhkd.android_seoulyeojido.RankRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhxorhkd.android_seoulyeojido.R;

/**
 * Created by 병윤 on 2016-10-24.
 */

public class RankViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    final View mView;
    final TextView rank;
    final ImageView profile;
    final TextView nickname;
    final TextView chk_cnt;
    final TextView ect;
    private RankAdapter rankAdapter;



    public RankViewHolder(View itemView, RankAdapter rankAdapter) {
        super(itemView);
        mView = itemView;
        rank = (TextView) itemView.findViewById(R.id.rank_order);
        nickname = (TextView)itemView.findViewById(R.id.nickname);
        chk_cnt = (TextView)itemView.findViewById(R.id.chk_cnt);
        ect = (TextView)itemView.findViewById(R.id.ect);
        profile = (ImageView)itemView.findViewById(R.id.profile_img);

        RelativeLayout rl = (RelativeLayout) itemView.findViewById(R.id.rank_list);
        rl.setOnClickListener(this);

        this.rankAdapter = rankAdapter;
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();

        switch (view.getId()){
            case R.id.rank_list :
                rankAdapter.clickEvent(position);
                break;
            default: break;
        }
    }
}