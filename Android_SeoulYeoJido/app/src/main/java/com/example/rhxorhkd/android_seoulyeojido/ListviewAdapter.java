package com.example.rhxorhkd.android_seoulyeojido;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rhxorhkd on 2016-10-15.
 */

public class ListviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Listviewitem> data;
    private int layout;
    private View tempview;
    public static int counts=0;
    public Context context;
    public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data){
        this.inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
            convertView = inflater.inflate(layout,parent,false);

        tempview = convertView;
        Listviewitem listviewitem = data.get(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.lay_imageview);

        setImagedata setImagedata = new setImagedata(icon);
        setImagedata.execute(listviewitem.getIcon());

        TextView name = (TextView)convertView.findViewById(R.id.lay_textview);
        name.setText(listviewitem.getName());

        TextView name2 = (TextView)convertView.findViewById(R.id.lay_textview2);
        name2.setText(listviewitem.getSubname());

        TextView name3 = (TextView)convertView.findViewById(R.id.lay_textview3);
        name3.setText(listviewitem.getThirdname());

        ImageView hearticon = (ImageView)convertView.findViewById(R.id.heartimage);
        hearticon.setImageResource(listviewitem.getHearticon());

        hearticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("null","clicked");
            }
        });

        Log.d("layout","position : "+position);
        Log.d("layout",""+listviewitem.getName());
        return convertView;
    }


    public class setImagedata extends AsyncTask<String, Void, String>{
        public  ImageView icon;
        public setImagedata(ImageView icon){
            this.icon=icon;
        }
        @Override
        protected String doInBackground(String... params) {
            return params[0];
        }
        @Override
        protected void onPostExecute(String s) {
            Glide.with(context).load(s).into(icon);
        }
    }
}
