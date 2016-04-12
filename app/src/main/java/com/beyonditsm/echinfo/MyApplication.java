package com.beyonditsm.echinfo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by wangbin on 16/4/1.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
