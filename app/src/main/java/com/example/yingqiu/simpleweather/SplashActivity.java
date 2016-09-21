package com.example.yingqiu.simpleweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yingqiu.simpleweather.homepage.widget.MainActivity;
import com.example.yingqiu.simpleweather.utils.CommonUtil;

public class SplashActivity extends AppCompatActivity {
    private static final String KEY_COPIED = "is_copied";
    private static final String SP_NAME = "config";
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = getSharedPreferences(SP_NAME,MODE_PRIVATE);
        boolean isCopied = sp.getBoolean(KEY_COPIED, false);
        if (!isCopied) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor edit = sp.edit();
                    CommonUtil.copyDatabase2FileDir(getApplicationContext(), "cities_china.db");
                    edit.putBoolean(KEY_COPIED, true);
                    edit.apply();
                    start();
                }
            }).start();

        } else {
            start();
        }
    }

    private void start() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sp = null;
    }
}
