package com.example.rhxorhkd.android_seoulyeojido.SetRecyclerView;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.Model.VisitedItem;
import com.example.rhxorhkd.android_seoulyeojido.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 병윤 on 2016-10-29.
 */

public class AnalysisAdapter extends RecyclerView.Adapter<AnlaysisViewHolder> {

    private Context mContext;
    private List<Integer> list;

    private TextView gu_name, percent;

    public AnalysisAdapter(Context mContext, List<Integer> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public AnlaysisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.analysis_item, parent, false);
        return new AnlaysisViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(AnlaysisViewHolder holder, int position) {
        int chk_cnt = list.get(position);
        switch (position){
            case 0 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =117;
                if(chk_cnt == 0){
                    Glide
                        .with(mContext)
                        .load(R.drawable.one)
                        .into(holder.gu_img);
                    holder.gu_name.setText("중구.종로");
                    holder.percent.setText("0%");


                }else{
                    Glide
                        .with(mContext)
                        .load(R.drawable.one_clear)
                        .into(holder.gu_img);
                    holder.gu_name.setText("중구.종로");
                    holder.percent.setText(""+getPercent(chk_cnt, 21)+"%");
                }
                break;
            case 1 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =117;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.two)
                            .into(holder.gu_img);
                    holder.gu_name.setText("성북.동대문.성동");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.two_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("성북.동대문.성동");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 2 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =98;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.three)
                            .into(holder.gu_img);
                    holder.gu_name.setText("서초.강남");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.three_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("서초.강남");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 3 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =98;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.four)
                            .into(holder.gu_img);
                    holder.gu_name.setText("송파.잠실");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.four_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("송파.잠실");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 4 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =90;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.five)
                            .into(holder.gu_img);
                    holder.gu_name.setText("마포.용산");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.five_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("마포.용산");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 5 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =90;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.six)
                            .into(holder.gu_img);
                    holder.gu_name.setText("관악.금천");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.six_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("관악.금천");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 6 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =85;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.seven)
                            .into(holder.gu_img);
                    holder.gu_name.setText("강서.양천.구로");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.seven_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("강서.양천.구로");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 7 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =85;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.eight)
                            .into(holder.gu_img);
                    holder.gu_name.setText("영등포.동작");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.eight_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("영등포.동작");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 8 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =94;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.nine)
                            .into(holder.gu_img);
                    holder.gu_name.setText("서대문.은평");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.nine_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("서대문.은평");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 9 :
                holder.gu_img.getLayoutParams().width =140;
                holder.gu_img.getLayoutParams().height =94;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.ten)
                            .into(holder.gu_img);
                    holder.gu_name.setText("중랑.광진");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.ten_clear)
                            .into(holder.gu_img);
                    holder.gu_name.setText("중랑.광진");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;
            case 10 :
                holder.gu_img.getLayoutParams().width = 140;
                holder.gu_img.getLayoutParams().height = 68;
                if(chk_cnt == 0){
                    Glide
                            .with(mContext)
                            .load(R.drawable.eleven)
                            .into(holder.gu_img);
                    holder.gu_name.setText("강동.도봉.노원");
                    holder.percent.setText("0%");


                }else{
                    Glide
                            .with(mContext)
                            .load(R.drawable.eleven)
                            .into(holder.gu_img);
                    holder.gu_name.setText("강동.도봉.노원");
                    holder.percent.setText(""+getPercent(chk_cnt, 5)+"%");
                }
                break;

            default:break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getPercent(double chk, double total){


         double percent = (chk/total)*100.0;



        return (int)percent;

    }
}
