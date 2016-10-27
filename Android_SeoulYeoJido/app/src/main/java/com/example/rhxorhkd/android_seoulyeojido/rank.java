package com.example.rhxorhkd.android_seoulyeojido;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rhxorhkd.android_seoulyeojido.Model.RankItem;
import com.example.rhxorhkd.android_seoulyeojido.RankRecyclerView.RankAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class rank extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("member");
        FirebaseUser user = auth.getCurrentUser();


        findViewById(R.id.find_myRank).setOnClickListener(this);

        ArrayList<RankItem> list = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.rank_rc);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                for (DataSnapshot post: data.getChildren()) {
                    Log.d("내꺼!!!!", ""+post.child("nickname").getValue());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for (int i = 0; i < 100; i++) {
            int tmp = i % 3 + 1;
            int profile = 0;
            if (tmp == 1) profile = R.drawable.irene1;
            else if (tmp == 2) profile = R.drawable.irene2;
            else if (tmp == 3) profile = R.drawable.irene3;

            RankItem item = new RankItem(
                    "" + (i+1), "아이린" + i, "" + (100 - i), "여긴뭐적지", profile
            );
            list.add(i, item);
        }
        mAdapter = new RankAdapter(this, list);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_myRank:
//                    mLayoutManager.scrollToPosition(95);
                mLayoutManager.smoothScrollToPosition(mRecyclerView, null, 95);
                break;
            default:
                break;
        }
    }
}
