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

import com.facebook.applinks.AppLinkData;

import java.io.IOException;

public class ScrinActivity extends AppCompatActivity {
    ImageView imageView;
    int [] images = {R.drawable.b_1,R.drawable.b_2,R.drawable.b_3, R.drawable.b_4, R.drawable.b_5, R.drawable.b_6};
    private int imageCaunter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clot);
        imageView = findViewById(R.id.imageView);

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



    public void showSite(String uri) {
        try {

            final Bitmap backButton = BitmapFactory.decodeResource(getResources(), R.drawable.round_done_black_24dp);

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.enableUrlBarHiding();
            builder.setToolbarColor(Color.BLACK);
            builder.setShowTitle(false);
            builder.addDefaultShareMenuItem();
            builder.setCloseButtonIcon(backButton);

            if (!uri.startsWith("http://") && !uri.startsWith("https://")) {
                uri = "http://" + uri;
            }

            CustomTabsIntent customTabsIntent = builder.build();

            boolean chromeInstalled = false;
            for (ApplicationInfo applicationInfo : getPackageManager().getInstalledApplications(0)) {
                if (applicationInfo.packageName.equals("com.android.chrome")) {
                    chromeInstalled = true;
                    break;
                }
            }
            if (chromeInstalled) {
                customTabsIntent.intent.setPackage("com.android.chrome");
            }

            customTabsIntent.launchUrl(this, Uri.parse(uri));
        } catch (Resources.NotFoundException e) {

            if (BuildConfig.DEBUG) {
                Log.e("my Log" + getLocalClassName(), "showSite: " + e.toString());

                e.printStackTrace();
            }
        }
    }

}
