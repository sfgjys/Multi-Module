package com.zhq.umeng_share.activity;

import android.content.Intent;

import com.umeng.socialize.UMShareAPI;
import com.zhq.baselibrary.base.PermissionActivity;

public class ShareSundryContentActivity extends PermissionActivity {

    public void startShare() {
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 传递分享操作结果给友盟的API，在友盟的API中处理结果
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放
        UMShareAPI.get(this).release();
    }
}
