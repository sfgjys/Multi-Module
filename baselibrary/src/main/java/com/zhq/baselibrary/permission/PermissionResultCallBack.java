package com.zhq.baselibrary.permission;

import android.os.Bundle;

import com.zhq.baselibrary.base.AsyncCallback;

/**
 * 类的描述: 权限申请的结果回调
 */
public interface PermissionResultCallBack extends AsyncCallback {
    /**
     * 方法描述: 代码异常导致的权限申请失败
     */
    void onCodeThrowableFailure(Bundle bundle, Throwable e);
}
