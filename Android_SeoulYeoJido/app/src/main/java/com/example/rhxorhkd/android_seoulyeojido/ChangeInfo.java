package com.example.rhxorhkd.android_seoulyeojido;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class ChangeInfo extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv1, iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        iv1 = (ImageView)findViewById(R.id.change_img);
        findViewById(R.id.chage_back).setOnClickListener(this);
        findViewById(R.id.change_img).setOnClickListener(this);
        Glide.with(this).load(R.drawable.irene1).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv1){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(this.getView().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iv1.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static int REQ_CODE_SELECT_IMAGE = 100;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chage_back :
                finish();
                break;
            case R.id.change_img :
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ_CODE_SELECT_IMAGE);
                break;
            default:break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Glide.with(this).load(data.getData()).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv1) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(this.getView().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv1.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        }
    }
}
