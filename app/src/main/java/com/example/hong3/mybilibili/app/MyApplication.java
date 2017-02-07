package com.example.hong3.mybilibili.app;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by hong3 on 2016/11/29.
 */

public class MyApplication extends Application{

    private static Context instance;
    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {

        x.Ext.init(this);

        super.onCreate();
        instance = this;
    }

}
