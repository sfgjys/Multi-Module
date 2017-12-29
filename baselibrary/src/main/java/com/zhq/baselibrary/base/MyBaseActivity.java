package com.zhq.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.PermissionChecker;

import com.zhq.baselibrary.tool.CommonTools;

import java.util.ArrayList;
import java.util.List;

public class MyBaseActivity extends FragmentActivity {

    private static final String TAG = "MyBaseActivity";

    /**
     * 变量描述: 在使用requestPermissions方法申请权限的时候，作为参数三，最后用来在onRequestPermissionsResult回调方法中区分请求
     */
    private static final int PERMISSION_REQUEST_CODE = 9;
    /**
     * 变量描述:
     */
    private static final int SKIP_APP_INFO_SETTING_CODE = 8;
    /**
     * 变量描述: 权限请求结果的回调对象
     */
    private AsyncCallback mPermissionRequestResultCallback;

    /**
     * 方法描述: 请求权限
     *
     * @param context        注意必须得是Activity
     * @param permissionList 请求权限的列表
     */
    public void permissionToRequest(Context context, String[] permissionList, AsyncCallback asyncCallback) {
        if (asyncCallback == null) {
            CommonTools.showToast("权限请求结果的回调对象为null", context);
            return;
        }
        mPermissionRequestResultCallback = asyncCallback;
        // 验证传递过来的数据
        if (context == null) {
            mPermissionRequestResultCallback.onFailure(null, new Throwable("权限请求需要的Activity为null"));
            return;
        }
        if (!(context instanceof Activity)) {
            mPermissionRequestResultCallback.onFailure(null, new Throwable("权限请求需要的Activity不为Activity类型"));
            return;
        }
        if (permissionList == null || !(permissionList.length > 0)) {
            mPermissionRequestResultCallback.onFailure(null, new Throwable("权限数据有问题"));
            return;
        }
        Activity activity = (Activity) context;

        if (!permissionCheck(permissionList, activity)) {
            if (Build.VERSION.SDK_INT >= 23) {
                // 6.0 以上请求
                ActivityCompat.requestPermissions(activity, mNoPermissionList.toArray(new String[mNoPermissionList.size()]), PERMISSION_REQUEST_CODE);
            } else {
                // 6.0 以下在清单文件中没有声明相关权限
                StringBuilder stringBuilder = new StringBuilder();
                for (String noPermissionTip : mNoPermissionList) {
                    stringBuilder.append(noPermissionTip).append(";");
                }
                mPermissionRequestResultCallback.onFailure(null, new Throwable("本系统在6.0以下，但是没有在清单文件中申请以下权限: " + stringBuilder.toString()));
            }
        }
    }

    private List<String> mNoPermissionList = new ArrayList<>();

    /**
     * 方法描述: 权限检查，所有权限是否已经获取了相关权限
     *
     * @param permissionManifest 要查询的权限的清单
     */
    private boolean permissionCheck(String[] permissionManifest, Context context) {
        mNoPermissionList.clear();
        for (String permission : permissionManifest) {
            if (PermissionChecker.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                // 没有获取到权限的名称放入集合中
                mNoPermissionList.add(permission);
            }
        }
        return mNoPermissionList.size() == 0;
    }

    /**
     * 变量描述: 存储请求授权失败的权限
     */
    private List<String> failedPermissions = new ArrayList<>();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                failedPermissions.clear();

                // permissions是进行申请的权限数组，而grantResults是对应的申请结果的数组
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        failedPermissions.add(permissions[i]);
                    }
                }
                if (failedPermissions.size() == 0) {// 失败权限集合没有数据，说明成功了
                    mPermissionRequestResultCallback.onSuccess(null);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String noPermissionTip : failedPermissions) {
                        stringBuilder.append(noPermissionTip).append(";");
                    }
                    mPermissionRequestResultCallback.onFailure(null, new Throwable(stringBuilder + " 权限被拒绝了"));
                }
                break;
            case SKIP_APP_INFO_SETTING_CODE:
                System.out.println();
                break;
        }
    }

    /**
     * 方法描述: 跳转至应用系统设置信息界面
     */
    public void skipAppInfoSettingActivity() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, SKIP_APP_INFO_SETTING_CODE);
    }
}
