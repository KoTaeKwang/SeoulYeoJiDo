package com.example.rhxorhkd.android_seoulyeojido;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rhxorhkd.android_seoulyeojido.Model.RankItem;
import com.example.rhxorhkd.android_seoulyeojido.RankRecyclerView.RankAdapter;

import java.util.ArrayList;

public class rank extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        findViewById(R.id.find_myRank).setOnClickListener(this);

        ArrayList<RankItem> list = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.rank_rc);

        for (int i = 0; i < 100; i++) {
            int tmp = i % 3 + 1;
            int profile = 0;
            if (tmp == 1) profile = R.drawable.irene1;
            else if (tmp == 2) profile = R.drawable.irene2;
            else if (tmp == 3) profile = R.drawable.irene3;

            RankItem item = new RankItem(
                    "" + (i + 1), "아이린" + i, "" + (100 - i), "여긴뭐적지", profile
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
