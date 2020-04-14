package com.cherryinc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.applinks.AppLinkData;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int [] images = {R.drawable.b_1,R.drawable.b_2,R.drawable.b_3, R.drawable.b_4, R.drawable.b_5, R.drawable.b_6};
    private int imageCaunter;
    private String DEPLINK = "pls://run";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        AppLinkData.fetchDeferredAppLinkData(getApplicationContext(), appLinkData -> {
            AppLinkData appLinkData1 = appLinkData;
            if (appLinkData1 == null || appLinkData1.getTargetUri() == null) {
                Log.e("MyLog", "deeplink = null");

                Log.e("MyLog", "вшз тгдд = " );


            } else {

                String url = appLinkData1.getTargetUri().toString();
                if (BuildConfig.DEBUG) {
                    Log.d("MyLog", "deeplink = ");
                    Log.d("MyLog", "deeplink = " + url);

                    Log.d("my Log", "App Link appLinkData: " + url);
                }
                String string = convertArrayToStringMethod(url.split(DEPLINK));

                if (BuildConfig.DEBUG) {
                    Log.d("MyLog", "App Link appLinkData url: " + url);

                    Log.d("MyLog", "App Link appLinkData string: " + string);
                }

                if (BuildConfig.DEBUG) {
                    Log.d("MyLog", "App Link appLinkData token: " + string);
                }


            }
        });

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

    public static String convertArrayToStringMethod(String[] strArray) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {

            stringBuilder.append(strArray[i]);

        }

        return stringBuilder.toString();

    }
}
