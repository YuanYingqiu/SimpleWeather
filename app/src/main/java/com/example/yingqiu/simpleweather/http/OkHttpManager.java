package com.example.yingqiu.simpleweather.http;

import com.example.yingqiu.simpleweather.http.callback.HttpFuture;
import com.example.yingqiu.simpleweather.http.callback.OkHttpCallback;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-13
 */
public class OkHttpManager {
    private static final String FIELD_STATUS = "status";
    private static final String STATUE_OK = "ok";


    private OkHttpManager() {
    }

    private static OkHttpManager manager;
    private static OkHttpClient client = new OkHttpClient();


    public static OkHttpManager getInstance() {
        if (manager == null) {
            synchronized (OkHttpManager.class) {
                if (manager == null)
                    manager = new OkHttpManager();
            }
        }
        return manager;
    }


    private void onFailure(final String statusCode, final OkHttpCallback callback) {
        callback.onFailure(statusCode);
    }


    private void onError(OkHttpCallback callback) {
        onFailure("网络连接错误", callback);
    }

    @SuppressWarnings("unchecked")
    private void onSuccess(Response response, OkHttpCallback callback) throws IOException {
        String res = response.body().string();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(res);
        JsonObject object = element.getAsJsonObject();

        String code = object.getAsJsonPrimitive(FIELD_STATUS).getAsString();
        if (STATUE_OK.equals(code)) {
            Object obj = new Gson().fromJson(res, callback.getGenericType());
            callback.onSuccess(obj);
        } else {
            onFailure(code, callback);
        }

    }


    //>>>>>>>>>>>>>>>>>>>>>>>  对外方法      >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public HttpFuture getAsync(String url, final OkHttpCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null)
                    OkHttpManager.this.onError(callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (callback != null) {
                        OkHttpManager.this.onSuccess(response, callback);
                    }

                } else {
                    if (callback != null)
                        OkHttpManager.this.onError(callback);
                }
            }
        });

        return new HttpFuture(call);
    }

    public HttpFuture postAsync(String url, Map<String, String> params, final OkHttpCallback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.add(key, value);
        }
        FormBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null)
                    OkHttpManager.this.onError(callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback != null) {
                    if (response.isSuccessful()) {
                        OkHttpManager.this.onSuccess(response, callback);
                    } else {
                        OkHttpManager.this.onError(callback);
                    }
                }
            }
        });

        return new HttpFuture(call);
    }





    //-------------------------和风天气--------------------------------------

    public HttpFuture asyncGet(String url, final OkHttpCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null)
                    OkHttpManager.this.onError(callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Object obj = new Gson().fromJson(json, callback.getGenericType());
                    //noinspection unchecked
                    callback.onSuccess(obj);

                } else {
                    if (callback != null)
                        OkHttpManager.this.onError(callback);
                }
            }
        });

        return new HttpFuture(call);
    }

}
