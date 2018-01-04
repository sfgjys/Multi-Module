package com.zhq.weixin_pay.client;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhq.baselibrary.tool.GsonTools;
import com.zhq.weixin_pay.server.WeiXinPayFieldsConfig;

import java.util.Locale;
import java.util.Random;

/**
 * 类的描述: 接收服务端传递过来的参数，开始微信支付调用
 */
public class WeiXinPay {

    /**
     * 方法描述: 使用参数二的信息去请求支付，参数二是通过服务端获取的
     */
    public static void pay(Context context, String payInfoBean) {
        IWXAPI wxApi = WXAPIFactory.createWXAPI(context, null);
        PayRequestBean payRequestBean = GsonTools.changeGsonToBean(payInfoBean, PayRequestBean.class);

        wxApi.registerApp(payRequestBean.appId);

        PayReq payReq = new PayReq();
        payReq.appId = payRequestBean.appId;// 微信开放平台审核通过的应用APPID
        payReq.partnerId = payRequestBean.partnerId;// 微信支付分配的商户号
        payReq.packageValue = payRequestBean.packageValue;// 暂填写固定值Sign=WXPay
        payReq.timeStamp = payRequestBean.timeStamp;// 时间戳  部分系统取到的值为毫秒级，需要转换成秒(10位数字),所以截取字符串前10位
        payReq.prepayId = payRequestBean.prepayId;
        payReq.nonceStr = payRequestBean.nonceStr;
        payReq.sign = payRequestBean.sign;

        wxApi.sendReq(payReq);
    }
}
