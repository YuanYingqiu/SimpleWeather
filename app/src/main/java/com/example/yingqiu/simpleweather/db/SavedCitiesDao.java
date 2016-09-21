package com.example.yingqiu.simpleweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yingqiu.simpleweather.homepage.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public class SavedCitiesDao {
    private static SavedCitiesDao dao;


    private final SQLiteDatabase db;

    private SavedCitiesDao(Context context){
        SavedCitiesOpenHelper helper = new SavedCitiesOpenHelper(
                context,SqlConst.DB_NAME,null,SqlConst.DB_VERSION);
        db = helper.getWritableDatabase();
    }

    public static SavedCitiesDao getInstance(Context context){
        if(dao == null){
            synchronized (SavedCitiesDao.class){
                if (dao == null)
                    dao = new SavedCitiesDao(context);
            }
        }
        return dao;
    }

    public List<String> loadAllSavedCities(){
        List<String> cities = new ArrayList<String>();
        Cursor cursor = db.query(SqlConst.SavedCities.TABLE_NAME,null,
                null,null,null,null,null);
        while (cursor.moveToNext()){
           String cityName = cursor.getString(cursor.getColumnIndex(SqlConst.SavedCities.COLUMN_CITY_NAME));
            cities.add(cityName);
        }
        cursor.close();
        return cities;
    }

    public long saveCity(City city){
        ContentValues values = new ContentValues();
        values.put(SqlConst.SavedCities.COLUMN_ID,city.getId());
        values.put(SqlConst.SavedCities.COLUMN_CITY_NAME,city.getCityName());
        values.put(SqlConst.SavedCities.COLUMN_PROVINCE_ID,city.getId());
        return db.insert(SqlConst.SavedCities.TABLE_NAME,null,values);
    }
}
