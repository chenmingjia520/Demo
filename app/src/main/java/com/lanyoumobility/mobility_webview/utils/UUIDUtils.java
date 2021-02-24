package com.lanyoumobility.mobility_webview.utils;


import java.util.UUID;

public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
