package com.example.yingqiu.simpleweather.http.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-13
 */
public abstract class OkHttpCallback<T> {

    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public OkHttpCallback(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }

    public Class<T> getGenericType(){
        return clazz;
    }


    public abstract void onSuccess(T t);
    public abstract void onFailure(String msg);

}
