package com.example.yingqiu.simpleweather.utils;

import android.content.Context;
import android.util.Xml;

import com.example.yingqiu.simpleweather.homepage.bean.City;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-17
 */
public class XmlUtil {
    private static final String FILE_NAME = "selected_cities.xml";
    private static final String ENCODING = "utf-8";

    private static final String TAG_CHINA = "China";
    private static final String TAG_CITY = "city";
    private static final String TAG_NAME = "name";


    /**
     * 会把原来的数据覆盖掉，所以需要先读取出来，再存进去
     *
     * @param context
     * @param cities
     */
    public static void write(Context context, List<City> cities) {

        File file = new File(context.getFilesDir(), FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            XmlSerializer xml = Xml.newSerializer();
            xml.setOutput(outputStream, ENCODING);

            xml.startDocument(ENCODING, true);

            xml.startTag(null, TAG_CHINA);

            for (City city : cities) {
                xml.startTag(null, TAG_CITY);
                xml.startTag(null, TAG_NAME);
                xml.text(city.getCityName());
                xml.endTag(null, TAG_NAME);
                xml.endTag(null, TAG_CITY);
            }

            xml.endTag(null, TAG_CHINA);
            xml.endDocument();


            outputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static List<City> read(Context context) {
        List<City> cities = null;
        InputStream inStream = null;
        File file = new File(context.getFilesDir(), FILE_NAME);
        try {

            inStream = new FileInputStream(file);
            XmlPullParser xml = Xml.newPullParser();
            xml.setInput(inStream, ENCODING);
            int type = xml.getEventType();
            City city = null;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if (TAG_CHINA.equals(xml.getName()))
                            cities = new ArrayList<City>();
                        else if (TAG_CITY.equals(xml.getName()))
                            city = new City();
                        else if (TAG_NAME.equals(xml.getName())) {
                            assert city != null;
                            city.setCityName(xml.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (TAG_CITY.equals(xml.getName())) {
                            assert cities != null;
                            cities.add(city);
                        }
                        break;
                }
                type = xml.next();
            }


        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return cities;
    }
}
