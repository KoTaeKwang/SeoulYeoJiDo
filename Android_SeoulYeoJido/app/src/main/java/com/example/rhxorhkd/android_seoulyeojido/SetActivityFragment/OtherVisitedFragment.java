package com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhxorhkd.android_seoulyeojido.Model.VisitedItem;
import com.example.rhxorhkd.android_seoulyeojido.R;
import com.example.rhxorhkd.android_seoulyeojido.SetRecyclerView.OtherVisitedAdapter;
import com.example.rhxorhkd.android_seoulyeojido.SetRecyclerView.VisitedAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OtherVisitedFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FirebaseDatabase db;
    private DatabaseReference ref;

    public OtherVisitedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_other_visited, container, false);
        // Inflate the layout for this fragment

        mRecyclerView = (RecyclerView)v.findViewById(R.id.other_visited_rc);
        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("member");

        final ArrayList<VisitedItem> list = new ArrayList<>();

        mAdapter = new OtherVisitedAdapter(this.getContext(), list);
        mRecyclerView.setAdapter(mAdapter);

        Intent i = getActivity().getIntent();
        String uid = i.getStringExtra("uid");

        ref.child(uid+"/checkin").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String s) {
                list.clear();
                final int size = list.size();
                final String photo = ""+data.child("img").getValue();
                final String title = ""+data.getKey();
//                String cnt = ;
                final String cate = "랜드마크";//""+data.child("category").getValue();
                final String guName = "은평구";//getGuName(Integer.parseInt(""+data.child("guNumber").getValue()));
                ref.addValueEventListener(new ValueEventListener() {
                    int chk_cnt = 0;
                    @Override
                    public void onDataChange(DataSnapshot cnt) {
                        for (DataSnapshot post: cnt.getChildren()) {
                            if(post.child("checkin") != null){
                                if(post.child("checkin").child(title).exists()){
                                    chk_cnt++;
                                }
                            }
                        }
                        list.add(size, new VisitedItem(photo, title, ""+chk_cnt, cate, guName));
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


        return v;
    }
    public String getGuName(int guNumber){
        if (guNumber ==1 ){
            return "은평";
        }else{
            return "";
        }
    }




}
