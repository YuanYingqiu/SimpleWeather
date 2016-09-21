package com.example.yingqiu.simpleweather.homepage.presenter;

import com.example.yingqiu.simpleweather.homepage.bean.City;
import com.example.yingqiu.simpleweather.homepage.bean.Province;
import com.example.yingqiu.simpleweather.homepage.callback.IMainCallback;
import com.example.yingqiu.simpleweather.homepage.model.IMainModel;
import com.example.yingqiu.simpleweather.homepage.model.MainModelImp;
import com.example.yingqiu.simpleweather.homepage.view.IMainView;
import com.example.yingqiu.simpleweather.http.callback.HttpCallback;

import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public class MainPresenterImp implements IMainPresenter<IMainView> ,IMainCallback {
    private IMainView mainView;
    private IMainModel mainModel;

    public MainPresenterImp(IMainView mainView){
        attachView(mainView);
        mainModel = new MainModelImp();
    }

    @Override
    public void onAddCitySuccess(String city) {
        mainView.onAddCitySuccess(city);
    }

    @Override
    public void onLoadSavedCitiesSuccess(List<String> cities) {
        mainView.onLoadSavedCitiesSuccess(cities);
    }

    @Override
    public void onLoadProvincesSuccess(List<Province> provinces) {
        mainView.onLoadProvincesSuccess(provinces);
    }

    @Override
    public void onLoadCitiesByProvinceIdSuccess(List<String> cities) {
        mainView.onLoadCitiesByProvinceIdSuccess(cities);
    }

    @Override
    public void onFailure(String msg) {
        mainView.onFailure(msg);
    }

    @Override
    public void addCity(String city) {
        mainModel.addCity(city,this);
    }

    @Override
    public void loadSavedCities() {
        mainModel.loadSavedCities(this);
    }

    @Override
    public void loadProvinces() {
        mainModel.loadProvince(this);
    }

    @Override
    public void loadCities(int provId) {
        mainModel.loadCities(provId,this);
    }

    @Override
    public void attachView(IMainView view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }
}
