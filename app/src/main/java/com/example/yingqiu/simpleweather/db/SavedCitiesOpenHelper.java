package com.example.yingqiu.simpleweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public class SavedCitiesOpenHelper extends SQLiteOpenHelper {

    public SavedCitiesOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlConst.SavedCities.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
