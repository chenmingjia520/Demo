package com.lanyoumobility.mobility_webview.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * /** 程序崩溃 异常捕获 保存到sd卡
 *
 * @ClassName: CrashHandler
 * @Description:
 * @author feng
 * @date 2015-6-22
 * @see
 *
 */
public class CrashHandler implements UncaughtExceptionHandler {

	private static CrashHandler myCrashHandler;
	private Context context;

	private CrashHandler() {

	}

	public static synchronized CrashHandler getInstance() {
		if (myCrashHandler == null) {
			myCrashHandler = new CrashHandler();
		}
		return myCrashHandler;
	}

	public void init(Context context) {
		this.context = context;
	}

	/**
	 * 程序崩溃 异常捕获 保存到sd卡
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		StringBuilder sb = new StringBuilder();
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(),
					0);
			sb.append("程序的版本号为" + packinfo.versionName);
			sb.append("\n");

			Field[] fields = Build.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				String name = fields[i].getName();
				sb.append(name + " = ");
				String value = fields[i].get(null).toString();
				sb.append(value);
				sb.append("\n");
			}
			StringWriter writer = new StringWriter();
			ex.printStackTrace(new PrintWriter(writer));
			String result = writer.toString();
			sb.append(result);
			if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
				File dir = new File(Config.PATHS_ERROR);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// 文档名称
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");// HH,24h
				String newdate = sdf.format(new Date());
				File file = new File(Config.PATHS_ERROR+ "/error_log_"
						+ newdate + ".txt");

				FileWriter fw = new FileWriter(file);
				fw.write(sb.toString());
				fw.close();
			}
			Log.i("CrashHandler", sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 杀死自身
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
