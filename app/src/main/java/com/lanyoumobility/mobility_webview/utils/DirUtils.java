package com.lanyoumobility.mobility_webview.utils;

import java.io.File;

import android.os.Environment;

/**
 * 文件夹/目录的工具类
 * 
 * @ClassName: DirUtils
 * @Description:
 * @author feng
 * @date 2016-12-6
 * @see
 */
public class DirUtils {

	private static final String TAG = "DirUtils";

	/**
	 * 创建所需的目录
	 */
	public static void initDir() {

		new Thread(new Runnable() {
			public void run() {
				// 确认SD卡可用
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {

					L.i(TAG, "开始创建目录...");

					// 根目标路径PATHS_ROOT: /zyd
					File rootdir = new File(Config.PATHS_ROOT);
					if (!rootdir.exists()) {
						rootdir.mkdir();
					}
					// 临时目录(打印文件等)PATHS_TEMP: /zyd/temp
					File imgdir = new File(Config.PATHS_IMG);
					if (!imgdir.exists()) {
						imgdir.mkdir();
					}

					// 临时目录(打印文件等)PATHS_TEMP: /zyd/temp
					File errordir = new File(Config.PATHS_ERROR);
					if (!errordir.exists()) {
						errordir.mkdir();
					}

					// 临时目录(打印文件等)PATHS_TEMP: /zyd/temp
					File exceldir = new File(Config.PATHS_EXCEL);
					if (!exceldir.exists()) {
						exceldir.mkdir();
					}

					// 临时目录(打印文件等)PATHS_TEMP: /zyd/temp
					File datadir = new File(Config.PATHS_DATA);
					if (!datadir.exists()) {
						datadir.mkdir();
					}

					// 临时目录(打印文件等)PATHS_TEMP: /zyd/temp
					File printingdir = new File(Config.PATHS_PRINTING);
					if (!printingdir.exists()) {
						printingdir.mkdir();
					}

					// 文件缓存目录
					File filecachedir = new File(Config.PATHS_FILECACHE);
					if (!filecachedir.exists()) {
						filecachedir.mkdir();
					}
				} else {
					L.i(TAG, "SD卡不可用!");
				}
			}
		}).start();
	}

}
