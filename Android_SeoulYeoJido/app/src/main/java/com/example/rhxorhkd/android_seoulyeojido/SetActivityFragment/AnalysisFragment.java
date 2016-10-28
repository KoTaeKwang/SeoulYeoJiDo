package com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.Model.VisitedItem;
import com.example.rhxorhkd.android_seoulyeojido.R;
import com.example.rhxorhkd.android_seoulyeojido.SetRecyclerView.AnalysisAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AnalysisFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase db;
    private DatabaseReference ref;

    public AnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_analysis, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("member");
        user = auth.getCurrentUser();

        mRecyclerView = (RecyclerView)v.findViewById(R.id.analysis_rv);
        mLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        final ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<11; i++){
            list.add(i, 0);
        }

        mAdapter = new AnalysisAdapter(getContext(), list);
        mRecyclerView.setAdapter(mAdapter);



        ref.child(user.getUid()+"/checkin").addChildEventListener(new ChildEventListener() {

            int guNumber_1, guNumber_2, guNumber_3, guNumber_4, guNumber_5, guNumber_6, guNumber_7,
                    guNumber_8, guNumber_9, guNumber_10, guNumber_11;

            int index;
            int chk_cnt;
            @Override
            public void onChildAdded(DataSnapshot data, String s) {



                if(data.child("guNumber") != null) {
                    index = Integer.parseInt("" + data.child("guNumber").getValue())-1;
                    chk_cnt = list.get(index);
                    list.set(index, chk_cnt+1);
                    mAdapter.notifyDataSetChanged();
//                    switch (Integer.parseInt("" + data.child("guNumber").getValue())) {
//                        case 1:
//                            guNumber_1++;
//                            break;
//                        case 2:
//                            guNumber_2++;
//                            break;
//                        case 3:
//                            guNumber_3++;
//                            break;
//                        case 4:
//                            guNumber_4++;
//                            break;
//                        case 5:
//                            guNumber_5++;
//                            break;
//                        case 6:
//                            guNumber_6++;
//                            break;
//                        case 7:
//                            guNumber_7++;
//                            break;
//                        case 8:
//                            guNumber_8++;
//                            break;
//                        case 9:
//                            guNumber_9++;
//                            break;
//                        case 10:
//                            guNumber_10++;
//                            break;
//                        case 11:
//                            guNumber_11++;
//                            break;
//
//                        default:
//                            break;
//                    }//switch
                }



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

}
