package com.example.yingqiu.simpleweather.utils;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-17
 */
public class HoFengApiHelper {
    public static final String APP_KEY_HOFENG = "185acb39439542c7b235c8962eb24ea9";
    private static final String CODE_IMG_URL = "http://files.heweather.com/cond_icon/";

    public static String getImgUrl(String code){
        return CODE_IMG_URL+code+".png";
    }


}
