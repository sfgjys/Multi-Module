package com.zhq.umeng_share.activity;

import android.app.Activity;
import android.content.Intent;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhq.baselibrary.base.PermissionActivity;
import com.zhq.baselibrary.tool.CommonTools;

import java.util.Map;

/**
 * 类的描述: 用于获取三方平台用户信息的Activity
 */
public class GetPlatformUserInfoActivity extends PermissionActivity implements UMAuthListener {

    /**
     * 方法描述: 开启某个平台的授权并获取用户信息
     * <p>
     * 如果要对操作结果进行自定义的话，请重写 onStart onComplete onError onCancel 方法
     * </p>
     */
    public void getPlatformUserInfo(Activity activity, SHARE_MEDIA share_media) {
        // 开启某个平台的授权并获取用户信息
        UMShareAPI.get(activity).getPlatformInfo(activity, share_media, this);
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

    /**
     * 方法描述: 分享开始
     */
    @Override
    public void onStart(SHARE_MEDIA share_media) {
    }

    /**
     * 方法描述: 分享完成
     */
    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
    }

    /**
     * 方法描述: 分享出现错误
     */
    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        CommonTools.showToast(share_media.toSnsPlatform().mShowWord + "登录出现异常，请使用其它的登录/注册方式", getApplication());
    }

    /**
     * 方法描述: 分享取消
     */
    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        CommonTools.showToast("您取消了" + share_media.toSnsPlatform().mShowWord + "登录", getApplication());
    }
}
