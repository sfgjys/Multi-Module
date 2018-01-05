package com.zhq.ali_pay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;
import com.zhq.baselibrary.tool.CommonTools;

import java.util.Map;

public class AliPayClient {

    /**
     * 方法描述: 支付sdk的调用必须在一个子线程中
     */
    public static Map<String, String> aliPay(Activity activity, String orderInfo) {
        if (!CommonTools.isMainThread()) {
            // 子线程
            PayTask payTask = new PayTask(activity);
            return payTask.payV2(orderInfo, true);// 参数二是否启用一个加载控件
        } else {
            // 主线程
            CommonTools.showToast("支付必须在子线程中", activity);
            return null;
        }
    }

}
