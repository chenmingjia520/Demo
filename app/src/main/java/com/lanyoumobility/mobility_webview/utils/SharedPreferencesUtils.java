package com.lanyoumobility.mobility_webview.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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


	public static final String FILE_NAME = "lianyou_client";

	public static void putBean(Context context, String key, Object obj) {
		putBean(context, FILE_NAME, key, obj);
	}


	private static void putBean(Context context, String fileName, String key, Object obj) {
		if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				String string64 = new String(Base64.encode(baos.toByteArray(), 0));
				SharedPreferences sp = context.getApplicationContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString(key, string64);
				SharedPreferencesCompat.apply(editor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"the obj must implement Serializble");
		}

	}

	public static Object getBean(Context context, String key) {
		return getBean(context, FILE_NAME, key);
	}

	private static Object getBean(Context context, String fileName, String key) {
		Object obj = null;
		try {
			String base64 = context.getApplicationContext().getSharedPreferences(fileName, Context.MODE_PRIVATE).getString(key, "");
			if (base64.equals("")) {
				return null;
			}
			byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
			ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
		}
		return obj;
	}









	/**
	 * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
	 *
	 * @author zhy
	 */
	private static class SharedPreferencesCompat {
		private static final Method sApplyMethod = findApplyMethod();

		/**
		 * 反射查找apply的方法
		 *
		 * @return
		 */
		@SuppressWarnings({"unchecked", "rawtypes"})
		private static Method findApplyMethod() {
			try {
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 如果找到则使用apply执行，否则使用commit
		 *
		 * @param editor
		 */
		public static void apply(SharedPreferences.Editor editor) {
			try {
				if (sApplyMethod != null) {
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			editor.commit();
		}

	}



}
