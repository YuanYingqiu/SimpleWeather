package com.example.yingqiu.simpleweather.http.callback;


import okhttp3.Call;

public class HttpFuture {
    private Call call;
    public HttpFuture(Call call){
        this.call = call;
    }

    public boolean isCanceled(){
        return call.isCanceled();
    }

    public boolean isExecuted(){
        return call.isExecuted();
    }

    public void cancel(){
        call.cancel();
    }
}
