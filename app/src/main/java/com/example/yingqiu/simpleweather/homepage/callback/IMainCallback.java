package com.example.yingqiu.simpleweather.homepage.callback;

import com.example.yingqiu.simpleweather.homepage.bean.City;
import com.example.yingqiu.simpleweather.homepage.bean.Province;

import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public interface IMainCallback {
    void onAddCitySuccess(String city);
    void onLoadSavedCitiesSuccess(List<String> cities);
    void onLoadProvincesSuccess(List<Province> provinces);
    void onLoadCitiesByProvinceIdSuccess(List<String> cities);
    void onFailure(String msg);
}
