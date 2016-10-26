package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rhxorhkd.android_seoulyeojido.R;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImageGridActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    Response response;
    Request request;
    GridView gridView;
    JSONArray array;
    JSONObject object;
    public class showDataDetail extends AsyncTask<String, Void, String> {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);
        Intent intent = getIntent();
        final String locationtitle = intent.getStringExtra("title");
        showDataDetail showDataDetail = new showDataDetail();
        String result =null;
        try {
            result = showDataDetail.execute(locationtitle).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 그리드 어댑터 세팅: 이미지 처리를 위한 이미지 어댑터를 그리드뷰에 전달
        gridView = (GridView)findViewById(R.id.gridView01);
        ImageGridAdapter imageAdapter = new ImageGridAdapter(this,result);
        gridView.setAdapter(imageAdapter);

        // 이벤트 처리를 위한 부분
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ImageViewPager.class);

                Toast.makeText(getApplicationContext(), (++position)+"번째 이미지 선택", Toast.LENGTH_SHORT).show();

                intent.putExtra("id", position);
                intent.putExtra("title",locationtitle);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), (++position)+"번째 이미지 선택", Toast.LENGTH_SHORT).show();
            }
        });
    }
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

}
