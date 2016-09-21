package com.example.yingqiu.simpleweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.yingqiu.simpleweather.App;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-13
 */
public class CommonUtil {
    /**
     * 判断当前网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 复制数据库到 FileDir
     * @param context 上下文 AppContext
     * @param dbName 数据库的名字
     */
    public static void copyDatabase2FileDir(Context context,String dbName) {
        InputStream stream = null;
        BufferedOutputStream outputStream = null;
        try {

            stream = context.getAssets().open(dbName);
            File file = new File(context.getFilesDir(),dbName);

            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            int len = 0 ;
            byte[] buf = new byte[1024];
            while ((len = stream.read(buf))!=-1){
                outputStream.write(buf,0,len);
                outputStream.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
