package com.example.yingqiu.simpleweather.http.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-13
 */
public interface HttpCallback<T> {
    void onSuccess(T t);
    void onFailure(String msg);
}
