package com.example.administrator.mybasemvp.base;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.administrator.mybasemvp.utils.CrashHandler;
import com.example.administrator.mybasemvp.utils.ForegroundCallbacks;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


public class BaseApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        if (initLeakCanary()) return;
        ForegroundCallbacks.init(this);
        CrashHandler.getInstance().init(getApplicationContext());
        initLogger();
    }

    private boolean initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return true;
        }
        LeakCanary.install(this);
        return false;
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static Context getContext() {
        return mContext;
    }


}
