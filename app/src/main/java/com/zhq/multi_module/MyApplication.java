package com.zhq.multi_module;

import android.app.Application;

import com.zhq.jpush.tool.JPUSHLogTools;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 极光推送初始化
        JPUSHLogTools.LOG_ENABLE = true;      // 设置是否开启极光推送自定义日志打印
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush

    }
}
