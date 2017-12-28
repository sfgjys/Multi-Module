package com.zhq.baselibrary.tool;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 类的描述: 常用的工具
 */
public class CommonTools {

    /**
     * 方法描述: 在主线程中弹吐司
     */
    public static void showToast(final String toast, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

}
