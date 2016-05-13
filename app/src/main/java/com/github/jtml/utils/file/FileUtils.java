package com.github.jtml.utils.file;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pengjf on 16/5/12.
 */
public class FileUtils {

    public static String getTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }

    public static String getTime2(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(time));
    }

    //删除文件
    public static void delFile(String fileName){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
        if(file.isFile()){
            file.delete();
        }
    }

    //删除文件
    public static void delFile(String filePath,String fileName){
        File file = new File(filePath + fileName);
        if(file.isFile()){
            file.delete();
        }
    }

    //split
    public static StringBuffer getString(String path){
        String[] strings = path.split("/");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.length - 1; i++) {
            sb.append(strings[i] + "/");
        }
        return sb;
    }

    public static String getSdPath(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        return null;
    }

    public static boolean isExist(String path) {
        File file = new File(path);
        if (file.exists())
            return true;
        else
            return false;
    }
}
