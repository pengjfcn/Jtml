package com.github.jtml;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

import com.github.jtml.utils.AppContextUtil;
import com.github.jtml.utils.L;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by pengjf on 16/5/12.
 */
public class App extends Application {

    private static Context mApplicationContext;
    private List<Activity> activity_clear = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        AppContextUtil.init(this);
        L.e("----初始化App----", this.getClass().getName());

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        L.init();
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }

    public void exit() {
        L.e("----退出App----");
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancelAll();
     //   dealData();
        clearActivity();
        System.exit(0);
    }

    public void addClearActivity(Activity activity) {
        activity_clear.add(activity);
    }

    /**
     * 结束所有记录的activity
     */
    public void clearActivity() {
        for (Activity activity : activity_clear) {
            activity.finish();
        }
    }
}
