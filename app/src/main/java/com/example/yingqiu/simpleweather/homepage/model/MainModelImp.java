package com.example.yingqiu.simpleweather.homepage.model;

import android.content.Context;

import com.example.yingqiu.simpleweather.App;
import com.example.yingqiu.simpleweather.db.ChineseCitiesDao;
import com.example.yingqiu.simpleweather.db.SavedCitiesDao;
import com.example.yingqiu.simpleweather.homepage.bean.City;
import com.example.yingqiu.simpleweather.homepage.bean.LocationBean;
import com.example.yingqiu.simpleweather.homepage.bean.Province;
import com.example.yingqiu.simpleweather.homepage.callback.IMainCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public class MainModelImp implements IMainModel {


    @Override
    public void addCity(String cityName,IMainCallback callback) {
        ChineseCitiesDao citiesDao = ChineseCitiesDao.getInstance();
        SavedCitiesDao savedDao = SavedCitiesDao.getInstance(App.getAppContext());

        //根据名字获取城市的id 和 provId
        City city = citiesDao.getCityByCityName(cityName);

        //存入保存已选城市的数据库当中
        long length = savedDao.saveCity(city);

        if(length != -1)
            callback.onAddCitySuccess(cityName);
        else
            callback.onFailure("添加失败");
    }

    @Override
    public void loadSavedCities(IMainCallback callback) {
        SavedCitiesDao dao = SavedCitiesDao.getInstance(App.getAppContext());
        List<String> cities = dao.loadAllSavedCities();
        callback.onLoadSavedCitiesSuccess(cities);
    }

    @Override
    public void loadProvince(IMainCallback callback) {
        ChineseCitiesDao citiesDao = ChineseCitiesDao.getInstance();
        List<Province> provinces = citiesDao.loadAllProvinces();
        if (provinces.size() > 0)
            callback.onLoadProvincesSuccess(provinces);
        else
            callback.onFailure("加载各省失败");
    }

    @Override
    public void loadCities(int provId,IMainCallback callback) {
        ChineseCitiesDao citiesDao = ChineseCitiesDao.getInstance();
        List<String> cities = citiesDao.getCitiesByProvinceId(provId);
        if (cities.size()>0)
            callback.onLoadCitiesByProvinceIdSuccess(cities);
        else
            callback.onFailure("加载城市失败");
    }

    @Override
    public void accordingLocationLoadCity(String url, LocationBean bean, IMainCallback callback) {

    }

    @Override
    public void accordingIpLoadCity(String url, String ip, IMainCallback callback) {

    }
}
