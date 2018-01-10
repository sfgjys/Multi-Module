package com.zhq.umeng_share.activity;

import android.content.Intent;
import android.content.res.Configuration;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhq.baselibrary.base.PermissionActivity;
import com.zhq.baselibrary.tool.CommonTools;

public class ShareSundryContentActivity extends PermissionActivity implements UMShareListener {

    private ShareAction mShareAction;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 对于友盟面板分享操作的规避: 屏幕横竖屏切换时避免出现window leak的问题
        if (mShareAction != null) {
            mShareAction.close();
        }
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
     * 方法描述: 往参数二的平台分享参数一的内容
     */
    public void shareSinglePlatform(ShareAction shareAction, SHARE_MEDIA shareMedia) {
        if (shareAction != null && shareMedia != null) {
            shareAction.setPlatform(shareMedia).setCallback(this).share();
        } else {
            CommonTools.showToast("平台或者分享内容不可为空", getApplication());
        }
    }

    /**
     * 方法描述: 使用参数二的配置展示分享面板，而面板上显示的分享平台由参数三决定，点击分享面板上的平台的点击事件由参数一决定
     */
    public void openMenuShare(ShareBoardlistener shareBoardlistener, ShareBoardConfig shareBoardConfig, SHARE_MEDIA... shareMedias) {
        if (mShareAction == null) {
            mShareAction = new ShareAction(ShareSundryContentActivity.this).setDisplayList(shareMedias).setShareboardclickCallback(shareBoardlistener);
        }
        if (shareBoardConfig != null) {
            mShareAction.open(shareBoardConfig);
        } else {
            mShareAction.open();
        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        // TODO 需要显示一个全局的加载对话框
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        CommonTools.showToast(share_media.toSnsPlatform().mShowWord + "平台分享成功", getApplication());
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        CommonTools.showToast(share_media.toSnsPlatform().mShowWord + "平台分享出错", getApplication());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        CommonTools.showToast(share_media.toSnsPlatform().mShowWord + "平台分享取消", getApplication());
    }
}
