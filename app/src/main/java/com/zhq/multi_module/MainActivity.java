package com.zhq.multi_module;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.zhq.baselibrary.base.AsyncCallback;
import com.zhq.baselibrary.base.MyBaseActivity;

public class MainActivity extends MyBaseActivity {

    //    android.Manifest.permission.BODY_SENSORS,
//    android.Manifest.permission.READ_CALENDAR,
//    android.Manifest.permission.CAMERA,
//    android.Manifest.permission.READ_CONTACTS,
//    android.Manifest.permission.ACCESS_FINE_LOCATION,
//    android.Manifest.permission.RECORD_AUDIO,

    //    android.Manifest.permission.READ_PHONE_STATE,
//    android.Manifest.permission.READ_EXTERNAL_STORAGE,
    String[] strings1 = {

            android.Manifest.permission.SEND_SMS,


    };
    String[] strings2 = {
            "允许应用访问手机状态",
            "允许应用访问手机状态", "允许应用访问手机状态", "允许应用访问手机状态", "允许应用访问手机状态",
            "允许应用写入外部存储",
            "允许应用通过COARSE获取位置信息",
            "允许应用通过FINE获取位置信息"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionToRequest(this, strings1, strings2, new AsyncCallback() {
            @Override
            public void onSuccess(Bundle bundle) {
                System.out.println();
            }

            @Override
            public void onFailure(Bundle bundle, Throwable e) {
                System.out.println();
            }
        });
    }
}
