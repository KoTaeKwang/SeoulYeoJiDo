package com.example.rhxorhkd.android_seoulyeojido.RankRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.rhxorhkd.android_seoulyeojido.Model.RankItem;
import com.example.rhxorhkd.android_seoulyeojido.R;
import com.example.rhxorhkd.android_seoulyeojido.set;

import java.util.List;

/**
 * Created by 병윤 on 2016-10-24.
 */

public class RankAdapter extends RecyclerView.Adapter<RankViewHolder>{

    private Context mContext;
    private List<RankItem> list;

    public RankAdapter(Context context, List<RankItem> list) {
        this.list = list;
        mContext = context;
    }

    @Override
    public RankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_item, parent, false);

        return new RankViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(final RankViewHolder holder, int position) {
        RankItem item = list.get(position);
        holder.rank.setText(item.getRank());
        holder.chk_cnt.setText(item.getChk_cnt());
        holder.nickname.setText(item.getNickname());
        holder.ect.setText(item.getEct());

        Glide.with(mContext).load(item.getImg()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.profile){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.profile.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clickEvent(int position) {
        RankItem item = list.get(position);

        Intent i = new Intent(this.mContext, set.class);
        i.putExtra("name", item.getNickname());
        this.mContext.startActivity(i);

    }
}
