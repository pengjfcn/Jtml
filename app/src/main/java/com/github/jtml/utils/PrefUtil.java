package com.github.jtml.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.jtml.App;
import com.github.jtml.Const;

/**
 * Created by pengjf on 16/5/12.
 */
public class PrefUtil {

    private static final String PRE_NAME = "io.github.laucherish.purezhihud_preferences";
    private static final String PRE_NIGHT = "night";

    private static SharedPreferences getSharedPreferences() {
        return App.getContext()
                .getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
    }

    public static void setNight(){
        getSharedPreferences().edit().putBoolean(PRE_NIGHT, true).commit();
    }

    public static void setDay(){
        getSharedPreferences().edit().putBoolean(PRE_NIGHT, false).commit();
    }

    public static void changeDayNight(){
        boolean change = !getSharedPreferences().getBoolean(PRE_NIGHT, false);
        getSharedPreferences().edit().putBoolean(PRE_NIGHT, change).commit();
    }

    public static boolean isNight(){
        return getSharedPreferences().getBoolean(PRE_NIGHT, false);
    }

    public static int getThemeRes(){
        if (!isNight()) {
            return Const.RESOURCES_DAYTHEME;
        } else {
            return Const.RESOURCES_NIGHTTHEME;
        }
    }
}
