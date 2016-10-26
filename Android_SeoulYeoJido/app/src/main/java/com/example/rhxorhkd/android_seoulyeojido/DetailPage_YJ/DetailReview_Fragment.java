package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rhxorhkd.android_seoulyeojido.R;

import java.util.ArrayList;

/**
 * Created by hanyoojin on 2016. 10. 26..
 */

public class DetailReview_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ArrayList<DetailReview> data = new ArrayList<>();
        DetailReview data1 = new DetailReview("병윤사랑아이린1", "리뷰써줘1", R.drawable.irene1);
        DetailReview data2 = new DetailReview("병윤사랑아이린1", "리뷰써줘1", R.drawable.irene1);
        DetailReview data3 = new DetailReview("병윤사랑아이린1", "리뷰써줘1", R.drawable.irene1);

        data.add(data1);
        data.add(data2);
        data.add(data3);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_review_list, container, false);
        final DetailListviewAdapter Adapter = new DetailListviewAdapter(getActivity(), R.layout.detail_row, data);

        ListView listview = (ListView) view.findViewById(R.id.listView);
        listview.setAdapter(Adapter);


        return view;

    }
}
