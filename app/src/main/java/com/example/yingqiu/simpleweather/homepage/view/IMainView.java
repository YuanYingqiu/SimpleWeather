package com.example.yingqiu.simpleweather.homepage.view;


import com.example.yingqiu.simpleweather.homepage.bean.City;
import com.example.yingqiu.simpleweather.homepage.bean.Province;

import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public interface IMainView{
    void addCity(String cityName);
    void onAddCitySuccess(String city);

    void loadSavedCities();
    void onLoadSavedCitiesSuccess(List<String> cities);

    void loadProvinces();
    void onLoadProvincesSuccess(List<Province> provinces);

    void loadCitiesByProvinceId(int provinceId);
    void onLoadCitiesByProvinceIdSuccess(List<String> cities);

    void onFailure(String msg);

}
