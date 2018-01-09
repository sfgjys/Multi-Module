package com.zhq.multi_module;

import android.os.Bundle;

import com.zhq.baselibrary.base.PermissionActivity;

public class MainActivity extends PermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
