package com.example.rhxorhkd.android_seoulyeojido.SetActivityFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhxorhkd.android_seoulyeojido.Model.VisitedItem;
import com.example.rhxorhkd.android_seoulyeojido.R;
import com.example.rhxorhkd.android_seoulyeojido.RankRecyclerView.RankAdapter;
import com.example.rhxorhkd.android_seoulyeojido.SetRecyclerView.VisitedAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisitedFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public VisitedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_visited, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.visited_rc);
        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);



        ArrayList<VisitedItem> list = new ArrayList<>();
        for(int i = 0; i<100; i++){

            VisitedItem item = new VisitedItem(
                    "", "장소"+i, ""+i
            );
            list.add(i, item);
        }
        mAdapter = new VisitedAdapter(this.getContext(), list);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }


    @Override
    public void onClick(View view) {

    }
}
