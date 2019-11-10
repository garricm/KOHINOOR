package com.kohinoor.util;

import java.io.RandomAccessFile;

public class CommonUtil {

	public static boolean isFileAccessible(String fileName) {
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(fileName, "rw");
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (ra != null)
					ra.close();
			} catch (Exception e) {
			}
		}
	}
}
