package com.example.yingqiu.simpleweather.mvp;

import com.example.yingqiu.simpleweather.http.callback.HttpCallback;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public interface IModel<T> {
    void loadData(String url, HttpCallback<T> callback);
    void cancelLoading();
}
