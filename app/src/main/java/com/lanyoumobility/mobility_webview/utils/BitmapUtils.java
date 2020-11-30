package com.lanyoumobility.mobility_webview.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class BitmapUtils {

    /**
     * 将图片base64数据转化为bitmap
     *
     * @param imgBase64
     */
    public static Bitmap base64ToPicture(String imgBase64) {
        byte[] decode = Base64.decode(imgBase64, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        return bitmap;
    }


}
