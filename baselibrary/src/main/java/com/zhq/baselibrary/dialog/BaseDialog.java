package com.zhq.baselibrary.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhq.baselibrary.BaseFieldsConfig;

/**
 * 类的描述: 基础对话框
 */
public abstract class BaseDialog extends DialogFragment {

    private Bundle mTransmitData;

    public static BaseDialog newInstance(Class aClass, Bundle bundle) {
        try {
            Object object = aClass.newInstance();
            if (object instanceof BaseDialog) {
                BaseDialog baseDialog = (BaseDialog) object;
                baseDialog.setArguments(bundle);
                return baseDialog;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTransmitData = getArguments();
        int style = -1;
        int theme = -1;
        if (mTransmitData != null) {
            style = mTransmitData.getInt(BaseFieldsConfig.KEY_DIALOG_STYLE, -1);
            theme = mTransmitData.getInt(BaseFieldsConfig.KEY_DIALOG_THEME, -1);
        }
        setStyle(style == -1 ? DialogFragment.STYLE_NO_FRAME : style, theme == -1 ? 0 : theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateDialogView(inflater, container, savedInstanceState, mTransmitData);
    }

    public abstract View onCreateDialogView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, Bundle transmitData);
}
