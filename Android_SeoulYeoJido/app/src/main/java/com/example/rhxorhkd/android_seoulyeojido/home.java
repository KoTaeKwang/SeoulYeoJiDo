package com.example.rhxorhkd.android_seoulyeojido;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    ListviewAdapter adapter; //listview adapter
    SearchitemAdapter sadapter; //search adapter
    LinearLayout map; //지도
    LinearLayout searchlistview; //검색창누르면 나오는 리스트뷰
    Button button; // 지도 안의 버튼
    SearchView searchView; //서치뷰
    ListView lv; //리스트
    ArrayList<Searchitem> searchdatas; //서치 데이터들
    ListView listView1; //서치 리스트뷰들
    private static final String TAG = "DemoActivity";
    private SlidingUpPanelLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        map=(LinearLayout)findViewById(R.id.map);
        searchlistview=(LinearLayout)findViewById(R.id.searchlistview);
        button =(Button) findViewById(R.id.button);


        ImageView imageView6 =(ImageView)findViewById(R.id.imageView6);

        searchInit(); //서치리스트 초기화
        listInit(); //리스트 초기화

        mLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG,"onPanelSlide, offset "+slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG,"onPanelStateChanged" + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

    }

    public void listInit(){
        lv =(ListView) findViewById(R.id.list);

        ArrayList<Listviewitem> data = new ArrayList<>();
        Listviewitem data1 = new Listviewitem(R.drawable.ic_launcher,"one","ones","oness",R.drawable.heart1);
        Listviewitem data2 = new Listviewitem(R.drawable.ic_launcher,"two","twos","twoss",R.drawable.heart1);
        Listviewitem data3 = new Listviewitem(R.drawable.ic_launcher,"three","threes","thress",R.drawable.heart1);
        Listviewitem data4 = new Listviewitem(R.drawable.ic_launcher,"four","fours","fourss",R.drawable.heart1);
        Listviewitem data5 = new Listviewitem(R.drawable.ic_launcher,"five","fives","fivess",R.drawable.heart1);
        Listviewitem data6 = new Listviewitem(R.drawable.ic_launcher,"six","sixs","sixss",R.drawable.heart1);
        Listviewitem data7 = new Listviewitem(R.drawable.ic_launcher,"seven","sevens","sevenss",R.drawable.heart1);
        Listviewitem data8 = new Listviewitem(R.drawable.ic_launcher,"eight","eights","eightss",R.drawable.heart1);
        Listviewitem data9 = new Listviewitem(R.drawable.ic_launcher,"nine","nines","niness",R.drawable.heart1);
        Listviewitem data10 = new Listviewitem(R.drawable.ic_launcher,"ten","tens","tenss",R.drawable.heart1);
        Listviewitem data11 = new Listviewitem(R.drawable.ic_launcher,"eleven","elevens","envenss",R.drawable.heart1);

        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        data.add(data6);
        data.add(data7);
        data.add(data8);
        data.add(data9);
        data.add(data10);
        data.add(data11);

         adapter = new ListviewAdapter(this,R.layout.item,data);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = (String)adapter.getItem(position);
                //Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show(); //옮기고 이거써
                Toast.makeText(home.this,str,Toast.LENGTH_LONG).show();

            }
        });

    }

    public void searchInit(){
        listView1 = (ListView)findViewById(R.id.listview1);
        searchdatas = new ArrayList<>(); //장소일때 주소일때 0,1 구별해서 state 넣기
        Searchitem searchdata1 = new Searchitem("dog",R.drawable.heart2,0);
        Searchitem searchdata2 = new Searchitem("eagle",R.drawable.heart2,0);
        Searchitem searchdata3 = new Searchitem("pig",R.drawable.heart2,1);
        Searchitem searchdata4 = new Searchitem("tiger",R.drawable.heart2,1);
        Searchitem searchdata5 = new Searchitem("lion",R.drawable.heart2,0);
        Searchitem searchdata6 = new Searchitem("bird",R.drawable.heart2,1);
        Searchitem searchdata7 = new Searchitem("human",R.drawable.heart2,0);
        Searchitem searchdata8 = new Searchitem("ant",R.drawable.heart2,1);
        Searchitem searchdata9 = new Searchitem("kotaekwang",R.drawable.heart2,0);
        Searchitem searchdata10 = new Searchitem("baek",R.drawable.heart2,1);
        Searchitem searchdata11 = new Searchitem("seo",R.drawable.heart2,0);
        Searchitem searchdata12 = new Searchitem("han",R.drawable.heart2,1);

        searchdatas.add(searchdata1);
        searchdatas.add(searchdata2);
        searchdatas.add(searchdata3);
        searchdatas.add(searchdata4);
        searchdatas.add(searchdata5);
        searchdatas.add(searchdata6);
        searchdatas.add(searchdata7);
        searchdatas.add(searchdata8);
        searchdatas.add(searchdata9);
        searchdatas.add(searchdata10);
        searchdatas.add(searchdata11);
        searchdatas.add(searchdata12);

        sadapter = new SearchitemAdapter(this,R.layout.searchitem,searchdatas);
        listView1.setAdapter(sadapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("text","listview1clicked");
                String str = (String)sadapter.getItem(position);
                //Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show(); //옮기고 이거써
                Toast.makeText(home.this,str,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) { //펼쳐있을때 back 누르면 닫히는거
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //search
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_view,menu);
        MenuItem mSearch = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        MenuItemCompat.setOnActionExpandListener(mSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {  //서치 아이콘 누를때  expand
                Log.d("text","open");
                map.setVisibility(View.INVISIBLE); // map화면 가려짐
                searchlistview.setVisibility(View.VISIBLE); //searchlist 화면 켜짐
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) { //서치 끌때
                Log.d("text","close");
                searchlistview.setVisibility(View.INVISIBLE);
                map.setVisibility(View.VISIBLE);
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                return true;
            }
        });

        searchView  = (SearchView) MenuItemCompat.getActionView(mSearch);

        searchView.setSubmitButtonEnabled(true);
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Log.d("text",query); -> query 찍힘
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sadapter.filter(newText);
                Log.d("text",""+newText);
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }
}
