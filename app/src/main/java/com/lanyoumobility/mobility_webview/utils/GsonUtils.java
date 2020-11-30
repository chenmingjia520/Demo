package com.lanyoumobility.mobility_webview.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/17.
 */

public class GsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtils() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param cls  对象类
     * @return
     */

    public static <T> List<T> GsonToList(JSONArray jsonArray, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            if (gson != null) {
                JSONObject jsonObject = null;
                T t = null;
                for(int x = 0;x<jsonArray.length();x++){
                    jsonObject = jsonArray.getJSONObject(x);

                    t = gson.fromJson(jsonObject.toString(), cls);
                    list.add(t);
                }
                return list;
            }
        }catch (Exception e){
            L.i("Exception:::"+e.toString());

        }
        return null;
    }





    /**
     * 转成list
     *
     * @param gsonString  JSON 数据
     * @param cls  对象类
     * @return
     */
    public static <T> List<T>  GsonToListss(String gsonString ,Class<T> cls) {

        List<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonarray = parser.parse(gsonString).getAsJsonArray();
        for (JsonElement element : jsonarray
        ) {
            list.add(gson.fromJson(element, cls));


        }
        return list;
    }
    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 将bean转换成Json字符串
     * @param bean
     * @return
     */
    public static String beanToJSONString(Object bean) {
        if (gson != null) {
            return gson.toJson(bean);
        }
        return "";
    }
}
