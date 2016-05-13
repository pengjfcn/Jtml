package com.github.jtml.utils.file;

import com.github.jtml.utils.L;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by pengjf on 16/5/12.
 */
public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				L.e("" + e);
			}
		}
		return true;
	}
}
