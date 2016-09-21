package com.example.yingqiu.simpleweather.mvp;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-13
 */
public interface IView<T> {

    void loadData();
    void onLoadDataSuccess(T bean);
    void onLoadDataFailure(String msg);
}
