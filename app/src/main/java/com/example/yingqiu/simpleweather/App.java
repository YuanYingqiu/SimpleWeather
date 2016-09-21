package com.example.yingqiu.simpleweather;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-13
 */
public class App extends Application {
    private static Context context;
    public static Context getAppContext(){
        return context;
    }

    private static Handler handler;
    public static Handler getHandler(){
        return handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
    }
}
