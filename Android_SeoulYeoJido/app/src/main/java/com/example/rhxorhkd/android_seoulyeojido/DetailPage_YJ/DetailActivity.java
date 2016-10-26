package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.R;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class DetailActivity extends AppCompatActivity {
    Request request;
    Response response;
    OkHttpClient client = new OkHttpClient();
    FloatingActionButton fab;
    JSONObject object;
//    private RecyclerView lecyclerView;

    ListView lv; //리스트
    //DetailListviewAdapter adapter; //listview adapter

    ListFragment listFragment;



    public class showDataDetail extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            try {
                json.put("loca_name", params[0]);
            }catch(Exception e){
                e.printStackTrace();
            }
            RequestBody posData = RequestBody.create(JSON,json.toString());
            request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/showDetailLoca")
                    .post(posData)
                    .build();
            try{
                response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            //post gu num
            return null;
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 삭제

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String url =null;
        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        final String locationTitle = intent.getStringExtra("name");
        String result =null;
        showDataDetail showDataDetail = new showDataDetail();
        try {
            Log.d("list","aaa"+locationTitle);
            result = showDataDetail.execute(locationTitle).get();
            object = new JSONObject(result);
            JSONArray array = object.getJSONArray("loca_photo");
            url =array.get(0).toString();
           //url = array.getJSONObject(0).toString();
            Log.d("list","-->"+url);

        }catch (Exception e){
            e.printStackTrace();
        }

        loadBackdrop(url);     //이미지 로드

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(locationTitle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();



        listFragment = (ListFragment) fm.findFragmentById(R.id.listfragment);


       // listInit(); //리스트 초기화

       // initLayout();       //댓글 초기화
       // initData();         //댓글

        /**
         * 댓글 입력
         */
        Button btnReview = (Button)findViewById(R.id.btn_review);
        btnReview.setOnClickListener(mClickListener);

        /**
         * 전체 댓글보기
         */
        Button btnMore = (Button)findViewById(R.id.btn_more);
        btnMore.setOnClickListener(mClickListener);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello World", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_review:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity =
                            new Intent(DetailActivity.this, Dialog.class);
                    startActivity(intentSubActivity);
                    break;

                case R.id.btn_more:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity2 =
                            new Intent(DetailActivity.this, AllreviewActivity.class);
                    startActivity(intentSubActivity2);
                    break;
            }
        }
    };


    /**
     * 액션바 뒤로가기
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    public void listInit(){
//        lv =(ListView) findViewById(R.id.detailListview);
//
//        final ArrayList<DetailReview> data = new ArrayList<>();
//        DetailReview data1 = new DetailReview("병윤사랑아이린1","리뷰써줘1",R.drawable.irene1);
//        DetailReview data2 = new DetailReview("병윤사랑아이린1","리뷰써줘1",R.drawable.irene1);
//        DetailReview data3 = new DetailReview("병윤사랑아이린1","리뷰써줘1",R.drawable.irene1);
//
//        data.add(data1);
//        data.add(data2);
//        data.add(data3);
//
//        adapter = new DetailListviewAdapter(this,R.layout.detail_row,data);
//        lv.setAdapter(adapter);
//    }


    /**
     *
     */

//    private void initLayout(){
//
//        lecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
//    }
//
//    /**
//     * 데이터 초기화
//     */
//    private void initData(){
//
//        List<DetailReview> reviewList = new ArrayList<>();
//
//        for (int i =0; i<3; i ++){
//
//            DetailReview review = new DetailReview();
//
//            review.setImage(R.drawable.irene3);
//            review.setTitle("사용자명 "+ (i+1));
//            review.setReview("리뷰리뷰 써주세요 "+ (i+1));
//            reviewList.add(review);
//        }
//
//        lecyclerView.setAdapter(new DetailRecyclerAdapter(reviewList,R.layout.detail_row));
//        lecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        lecyclerView.setItemAnimator(new DefaultItemAnimator());
//
//    }


    /**
     * 이미지 로드
     */
    private void loadBackdrop(String url) {
        final ImageView imageview = (ImageView) findViewById(R.id.backdrop);

        //Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
        Glide.with(this).load(url).into(imageview);

    }

}
