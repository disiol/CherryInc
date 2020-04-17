package com.cherryinc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class ScrinActivity extends AppCompatActivity {
    ImageView imageView;
    int [] images = {R.drawable.b_1,R.drawable.b_2,R.drawable.b_3, R.drawable.b_4, R.drawable.b_5, R.drawable.b_6, R.drawable.b_7, R.drawable.b_8, R.drawable.b_9};
    private int imageCaunter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrin);
        imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(images[imageCaunter]));


    }


    @SuppressLint("ResourceType")
    public void onClick(View view) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
// Вычисляем размеры экрана
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        try {
            // wallpaperManager.setBitmap(bitmap);
            wallpaperManager.setResource(images[imageCaunter]);

            wallpaperManager.suggestDesiredDimensions(width, height);
            Toast.makeText(
                    this,
                    "Обои установлены",
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickReset(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        try {
            wallpaperManager.clear();
            Toast.makeText(
                    this,
                    "Обои сброшены",
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickNext(View view) {
        if(imageCaunter < images.length -1){
            imageCaunter ++;
        }else {
            imageCaunter = 0;
        }

        imageView.setImageDrawable(getResources().getDrawable(images[imageCaunter]));

    }


}
