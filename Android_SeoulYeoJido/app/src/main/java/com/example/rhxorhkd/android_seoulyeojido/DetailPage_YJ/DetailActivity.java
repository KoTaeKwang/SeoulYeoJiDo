package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rhxorhkd.android_seoulyeojido.R;
import com.example.rhxorhkd.android_seoulyeojido.RankRecyclerView.RankAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class DetailActivity extends AppCompatActivity {
    private GpsInfo gps;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Double longitude;
    private Double latitude;
    Intent intentSubActivity;
    String getreviewarray;
    String number;
    String weburl;
    String description = "내용없음";
    String address = "내용없음";
    String lat;
    String lon;
    String checkincount;
    String guNumber;
    Request request;
    Response response;
    OkHttpClient client = new OkHttpClient();
    FloatingActionButton fab;
    JSONObject object;
    JSONArray array;
    JSONArray reviewarray;
    String result = null;
    String locationTitle;
    private RecyclerView lecyclerView;
    ArrayList<DetailReview> list;
    ListView lv; //리스트
    //DetailListviewAdapter adapter; //listview adapter
    ListFragment listFragment;
    String url;
    DetailRecyclerAdapter detailRecyclerAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Detail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }

    public class showDataDetail extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            try {
                json.put("loca_name", params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestBody posData = RequestBody.create(JSON, json.toString());
            request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/showDetailLoca")
                    .post(posData)
                    .build();
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //post gu num
            return null;
        }
    }


    public class checkinsuccess extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            try {
                json.put("loca_name", params[0]);
                json.put("loca_lat", params[1]);
                json.put("loca_lon", params[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestBody posData = RequestBody.create(JSON, json.toString());
            request = new Request.Builder()
                    .url("http://211.189.20.136:4389/ko/checkin")
                    .post(posData)
                    .build();
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //post gu num
            return null;
        }
    }

    public void showdetailinit() {
        showDataDetail showDataDetail = new showDataDetail();
        //{"loca_photo":["http:\/\/contents.visitseoul.net\/file_save\/art_img\/2014\/01\/22\/20140122165615.jpg","http:\/\/contents.visitseoul.net\/file_save\/art_img\/2014\/01\/22\/20140122165635.jpg","http:\/\/contents.visitseoul.net\/file_save\/art_img\/2014\/01\/22\/20140122165650.jpg","http:\/\/contents.visitseoul.net\/file_save\/art_img\/2014\/01\/22\/20140122165707.jpg"],"loca_name":"소공 지하쇼핑센터","loca_address":"서울 중구 소공동 87-1","loca_latitude":"37.5644064","loca_longitude":"126.9796971","loca_tel":"02-775-2234","loca_description":"관광객에게 각광받는 쇼핑명소 서울시청에서 명동으로 이어지는 소공지하상가는 80년대 서울에서 가장 잘나가는 쇼핑 1번지로 꼽혔다. 현재도 인근의 롯데백화점, 프라자호텔과 연결되어 있어 관광객들의 이용도가 높다.소공지하상가의 토산품, 도자기, 민속 공예품 가게에는 아기자기한 저가의 관광상품이 많다. 일본인 관광객이 많아 일본어 가격표를 쉽게 볼 수 있다.통로 역시 넓고 쾌적하다. 롯데백화점 본점, 영플라자, 에비뉴엘 지하와 바로 연결되는 통로가 있어 연계된 쇼핑이 편리하다. ※ 이용 Tip한국은행 본점 옆길로 가서 웨스틴 조선 호텔쪽 입구로 들어가는 방법도 있지만 그 다음 코스를 시청역으로 잡는다면 명동 쪽에서 출발해 지하상가를 계속 따라가 시청으로 가는 방법이 편하다.\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n Address : 서울 중구 소공동 87-1&lt;br&gt; Tel : 02-775-2234&lt;br&gt; HomePage URL : http:\/\/sogongmall.com\/kor\/&lt;br&gt;","loca_checkincount":0,"loca_review":[],"loca_url":"http:\/\/sogongmall.com\/kor\/"}
        try {
            Log.d("list", "aaa" + locationTitle);
            result = showDataDetail.execute(locationTitle).get();
            object = new JSONObject(result);
            number = object.getString("loca_tel");

            description = object.getString("loca_description");
            address = object.getString("loca_address");
            lat = object.getString("loca_latitude");
            lon = object.getString("loca_longitude");
            checkincount = object.getString("loca_checkincount");
            reviewarray = object.getJSONArray("loca_review");
            array = object.getJSONArray("loca_photo");
            guNumber = object.getString("loca_guNum");
            if (array == null)
                Log.d("list", "null" + locationTitle);
            url = array.get(0).toString();
            //url = array.getJSONObject(0).toString();
            Log.d("list", "-->" + object);
            weburl = object.getString("loca_url");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 삭제
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//상태바 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        url = null;
        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        locationTitle = intent.getStringExtra("name");

        gps = new GpsInfo(DetailActivity.this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();

        showdetailinit();
        if (url != null)
            loadBackdrop(url);     //이미지 로드

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView back_btn = (ImageView) findViewById(R.id.back_btn1);
        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
               // Toast.makeText(getApplicationContext(), "back",Toast.LENGTH_SHORT).show();
            }
        });

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);
        //collapsingToolbar.setTitle(locationTitle);

        TextView loca_title1 = (TextView)findViewById(R.id.loca_title);
        loca_title1.setText(locationTitle);

        /**
         * 상세 버튼, 설명 etc...
         */
        ImageView imgTel = (ImageView) findViewById(R.id.img_frg1);
        ImageView imgURL = (ImageView) findViewById(R.id.img_frg2);
        ImageView imgMap = (ImageView) findViewById(R.id.img_frg3);

        TextView txt_address = (TextView) findViewById(R.id.detailaddress);
        txt_address.setText(address);
        TextView txt_description = (TextView) findViewById(R.id.txt_detail);
        txt_description.setText(description);

        imgTel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        imgMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getContext(), "map 연결",Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailActivity.this, DetailMapsActivity.class));
            }
        });

        imgURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(weburl));
                startActivity(intent);
            }
        });


        ImageView imageView1 = (ImageView) findViewById(R.id.img_photo1);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_photo2);
        ImageView imageView3 = (ImageView) findViewById(R.id.img_photo3);
        ImageView imageView4 = (ImageView) findViewById(R.id.img_photo4);

        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                if (i == 4) break;
                try {
                    String temp = array.get(i).toString();
                    if (i == 0) {
                        Glide.with(this).load(temp).into(imageView1);
                    } else if (i == 1) {
                        Glide.with(this).load(temp).into(imageView2);
                    } else if (i == 2) {
                        Glide.with(this).load(temp).into(imageView3);
                    } else if (i == 3) {
                        Glide.with(this).load(temp).into(imageView4);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        if (array != null) {
            if (array.length() > 3) {
                imageView4.setBackgroundColor(new Color().argb(255, 40, 40, 40));
                imageView4.setAlpha(50);

                imageView4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailActivity.this, ImageGridActivity.class);
                        intent.putExtra("title", locationTitle);
                        startActivity(intent);
                    }
                });
            }
        }
        /**
         * 댓글 리사이클뷰
         */

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        Log.d("list", "reviearray-->" + reviewarray.length());
        for (int i = 0; i < reviewarray.length(); i++) {
            try {
                JSONObject object = reviewarray.getJSONObject(i);
                String user_id = object.getString("user_id");
                String review_content = object.getString("review_content");
                String date = object.getString("date");
                DetailReview review = new DetailReview(user_id, review_content, date, R.drawable.irene3);
                list.add(review);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

       /* for (int i = 0; i < 3; i++) {

            DetailReview review = new DetailReview("사용자명 " + (i + 1),"리뷰리뷰 써주세요 " + (i + 1),R.drawable.irene3);
//            review.setImage(R.drawable.irene3);
//            review.setTitle("사용자명 " + (i + 1));
//            review.setReview("리뷰리뷰 써주세요 " + (i + 1));
            list.add(review);
        }*/
        detailRecyclerAdapter = new DetailRecyclerAdapter(list, R.layout.detail_row);
        mRecyclerView.setAdapter(detailRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        // listInit(); //리스트 초기화

        //initLayout();       //댓글 초기화
        //initData();         //댓글

        /**
         * 댓글 입력
         */
        Button btnReview = (Button) findViewById(R.id.btn_review);
        btnReview.setOnClickListener(mClickListener);

        /**
         * 전체 댓글보기
         */
        Button btnMore = (Button) findViewById(R.id.btn_more);
        btnMore.setOnClickListener(mClickListener);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = null;
                try {
                    checkinsuccess checkinsuccess = new checkinsuccess();
                    result = checkinsuccess.execute(locationTitle, latitude.toString(), longitude.toString()).get();
                    Log.d("list", "result : " + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), CheckinPopup.class);
                intent.putExtra("result", result); //result 가 0 이면 실패 , 1이면 성공
                intent.putExtra("title", locationTitle);

                startActivity(intent);
                // startActivity(new Intent(getApplicationContext(), CheckinPopup.class));
                //Snackbar.make(v, "Hello World", Snackbar.LENGTH_LONG).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_review:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    intentSubActivity =
                            new Intent(DetailActivity.this, Dialog.class);
                    intentSubActivity.putExtra("title", locationTitle);
                    startActivity(intentSubActivity);


                    break;

                case R.id.btn_more:
                    Log.d("OnClickListener", "click session button");
                    // 액티비티 실행
                    Intent intentSubActivity2 =
                            new Intent(DetailActivity.this, AllreviewActivity.class);

                    intentSubActivity2.putExtra("result", result);
                    startActivity(intentSubActivity2);
                    break;
            }
        }
    };

    /**
     * 액션바 뒤로가기
     *
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 이미지 로드
     */
    private void loadBackdrop(String url) {
        final ImageView imageview = (ImageView) findViewById(R.id.backdrop);

        //Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
        Glide.with(this).load(url).into(imageview);
    }

}
    /**
     *
     */
//    private void initLayout(){
//
//        lecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
//    }

    /**
     * 데이터 초기화
     */
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




