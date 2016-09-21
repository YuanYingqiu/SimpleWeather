package com.example.yingqiu.simpleweather.homepage.model;

import com.example.yingqiu.simpleweather.homepage.bean.City;
import com.example.yingqiu.simpleweather.homepage.bean.LocationBean;
import com.example.yingqiu.simpleweather.homepage.callback.IMainCallback;
import com.example.yingqiu.simpleweather.http.callback.HttpCallback;

import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public interface IMainModel{

    /**
     * 增加城市
     */
    void addCity(String city,IMainCallback callback);

    /**
     * 加载已经保存的城市
     */
    void loadSavedCities(IMainCallback callback);

    void loadProvince(IMainCallback callback);

    void loadCities(int provId,IMainCallback callback);

    /**
     * 根据经纬度获得当前城市
     */
    void accordingLocationLoadCity(String url,LocationBean bean,IMainCallback callback);

    /**
     * 根据IP获得当前城市
     */
    void accordingIpLoadCity(String url,String ip,IMainCallback callback);


}
