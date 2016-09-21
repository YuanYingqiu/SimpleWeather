package com.example.yingqiu.simpleweather.homepage.bean;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-17
 */
public class City {
    private String id;
    private String cityName;
    private int provId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getProvId() {
        return provId;
    }

    public void setProvId(int provId) {
        this.provId = provId;
    }
}
