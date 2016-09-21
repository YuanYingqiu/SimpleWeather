package com.example.yingqiu.simpleweather.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yingqiu.simpleweather.homepage.bean.City;
import com.example.yingqiu.simpleweather.homepage.bean.Province;

import java.util.ArrayList;
import java.util.List;


public class ChineseCitiesDao {
    private static ChineseCitiesDao dao;
    private static final String PATH = "data/data/com.example.yingqiu.simpleweather/files/cities_china.db";
    private static final String TABLE_CITIES = "Cities";
    private static final String TABLE_PROVINCE = "Provinces";


    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PROVINCE_ID = "prov";
    private static final String COLUMN_CITY_NAME = "city";

    private SQLiteDatabase db;


    private ChineseCitiesDao() {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    public static ChineseCitiesDao getInstance() {
        if (dao == null) {
            synchronized (ChineseCitiesDao.class) {
                if (dao == null)
                    dao = new ChineseCitiesDao();
            }
        }
        return dao;
    }

    public List<Province> loadAllProvinces() {
        List<Province> provinces = new ArrayList<Province>();
        Cursor cursor = db.query(TABLE_PROVINCE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Province province = new Province();
            province.setProvinceName(cursor.getString(cursor.getColumnIndex("prov")));
            province.setProvinceId(cursor.getInt(cursor.getColumnIndex("_id")));
            provinces.add(province);

        }
        cursor.close();

        return provinces;
    }


    public List<City> accordingProvinceIdGetCities(int provinceId) {
        List<City> cities = new ArrayList<City>();
        Cursor cursor = db.query(TABLE_CITIES, null, "prov", new String[]{String.valueOf(provinceId)},
                null, null, null);

        while (cursor.moveToNext()) {
            City city = new City();
            city.setId(cursor.getString(cursor.getColumnIndex("id")));
            city.setCityName(cursor.getString(cursor.getColumnIndex("city")));
            city.setProvId(cursor.getInt(cursor.getColumnIndex("prov")));
            cities.add(city);
        }

        cursor.close();
        return cities;
    }

    public City getCityByCityName(String cityName) {
//        Cursor cursor = db.query(TABLE_CITIES, new String[]{COLUMN_ID, COLUMN_PROVINCE_ID},
//                COLUMN_CITY_NAME, new String[]{cityName}, null, null, null);
        String sql = "select * from Cities where city = ?";

        Cursor cursor = db.rawQuery(sql,new String[]{cityName});


        City city = new City();
        if(cursor.moveToNext()){
            city.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            city.setCityName(cityName);
            city.setProvId(cursor.getInt(cursor.getColumnIndex(COLUMN_PROVINCE_ID)));
        }


        cursor.close();
        return city;
    }


    public List<String> getCitiesByProvinceId(int provinceId) {
        List<String> cities = new ArrayList<String>();
        String sql = "select city from "+TABLE_CITIES+" where prov = ?";
//        Cursor cursor = db.query(TABLE_CITIES, new String[]{"city"}, "prov", new String[]{String.valueOf(provinceId)},
//                null, null, null);
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(provinceId)});

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            cities.add(name);
        }

        cursor.close();
        return cities;
    }


}
