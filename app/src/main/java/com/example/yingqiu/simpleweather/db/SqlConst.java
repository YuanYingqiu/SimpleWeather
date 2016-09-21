package com.example.yingqiu.simpleweather.db;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public interface SqlConst {
    String DB_NAME = "saved_cities.db";
    int DB_VERSION = 1;

    interface SavedCities{
        String TABLE_NAME = "SavedCities";
        String ID = "_id";
        String COLUMN_ID = "id";
        String COLUMN_CITY_NAME = "cityName";
        String COLUMN_PROVINCE_ID = "provinceId";

        String CREATE_SQL = "create table "+TABLE_NAME+"( "
                +ID+" integer primary key autoincrement,"
                +COLUMN_ID+" text UNIQUE,"
                +COLUMN_CITY_NAME+" text,"
                +COLUMN_PROVINCE_ID+" integer"
                +")";
    }

}
