package com.example.rhxorhkd.android_seoulyeojido.DetailPage_YJ;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rhxorhkd.android_seoulyeojido.R;

public class ImageGridActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 그리드 어댑터 세팅: 이미지 처리를 위한 이미지 어댑터를 그리드뷰에 전달
        gridView = (GridView)findViewById(R.id.gridView01);
        ImageGridAdapter imageAdapter = new ImageGridAdapter(this);
        gridView.setAdapter(imageAdapter);

        // 이벤트 처리를 위한 부분
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ImageViewPager.class);

                Toast.makeText(getApplicationContext(), (++position)+"번째 이미지 선택", Toast.LENGTH_SHORT).show();

                intent.putExtra("id", position);
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
