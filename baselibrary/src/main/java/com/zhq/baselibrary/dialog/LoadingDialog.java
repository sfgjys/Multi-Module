package com.zhq.baselibrary.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhq.baselibrary.R;

/**
 * 类的描述: 全局加载对话框
 */
public class LoadingDialog extends DialogFragment {

    private static LoadingDialog mLoadingDialog;

    /**
     * 方法描述: 展示加载对话框
     */
    public static void showLoadingDialog(FragmentManager fragmentManager) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
            mLoadingDialog.setCancelable(false);
        } else {
            mLoadingDialog.show(fragmentManager, "LoadingDialog");
        }
    }

    /**
     * 方法描述: 关闭加载对话框
     */
    public static void closeLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_loading, null);
    }
}
