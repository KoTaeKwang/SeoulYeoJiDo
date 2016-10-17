package com.example.rhxorhkd.android_seoulyeojido;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhxorhkd on 2016-10-17.
 */

public class SearchitemAdapter extends BaseAdapter{

    LayoutInflater inflater;
    private ArrayList<Searchitem> data;
    private List<Searchitem> searchlist;
    private int layout;

    public SearchitemAdapter(Context context, int layout,List<Searchitem> searchlist){
        this.inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.searchlist = searchlist;
        this.data = new ArrayList<Searchitem>();
        this.data.addAll(searchlist);
        this.layout=layout;
    }


    @Override
    public int getCount() {
        return searchlist.size();
    }

    @Override
    public Object getItem(int position) {
        return searchlist.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = inflater.inflate(layout,parent,false);

        Searchitem searchitem =searchlist.get(position);

        TextView textview = (TextView)convertView.findViewById(R.id.searchname);
        textview.setText(searchitem.getName());

        return convertView;
    }

    public void filter(String charText){
        Log.d("text",""+charText.length());

        searchlist.clear();
        if(charText.length()==0){
            searchlist.addAll(data);
        }else{
            for(Searchitem wp : data){
                Log.d("text","-->"+wp.getName().contains(charText));
                if(wp.getName().contains(charText)){
                    Log.d("text","------>"+wp.getName());
                    searchlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
