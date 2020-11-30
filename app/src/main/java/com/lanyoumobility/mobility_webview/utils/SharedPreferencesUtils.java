package com.lanyoumobility.mobility_webview.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @ClassName: SharedPreferencesUtils
 * @Description: 操作SP的工具类
 * @author chenmingjia
 * @date 2015-1-15
 * @see
 */
public class SharedPreferencesUtils {

	public static String SP_NAME = "config_rent";
	private static SharedPreferences sp;

	/**
	 * 保存Boolean类型的数据
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(Context context, String key, boolean value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 获取Boolean类型的数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);

		}
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 保存String类型的数据
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static boolean saveString(Context context, String key, String value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.edit().putString(key, value).commit();
	}

	/**
	 * 获取String类型的数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);

		}
		return sp.getString(key, defValue);
	}

	/**
	 * 保存Int类型的数据
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveInt(Context context, String key, int value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * 获取Int类型的数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);

		}
		return sp.getInt(key, defValue);
	}


	/**
	 * 保存Int类型的数据
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveFloat(Context context, String key, float value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putFloat(key, value).commit();
	}

	/**
	 * 获取Int类型的数据
	 *
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Context context, String key, int defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);

		}
		return sp.getFloat(key, defValue);
	}

}
