package com.example.yingqiu.simpleweather.mvp;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-13
 */
public interface IPresenter<V> {
    void attachView(V view);
    void detachView();
}
