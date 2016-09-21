package com.example.yingqiu.simpleweather.homepage.presenter;

import com.example.yingqiu.simpleweather.homepage.bean.City;
import com.example.yingqiu.simpleweather.homepage.callback.IMainCallback;
import com.example.yingqiu.simpleweather.mvp.IPresenter;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public interface IMainPresenter<V> extends IPresenter<V>{
    void addCity(String city);
    void loadSavedCities();

    void loadProvinces();
    void loadCities(int provId);

}
