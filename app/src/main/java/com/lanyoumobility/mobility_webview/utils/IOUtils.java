package com.lanyoumobility.mobility_webview.utils;

import java.io.Closeable;
import java.io.IOException;


public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				L.e(e.getMessage());
			}
		}
		return true;
	}
}
