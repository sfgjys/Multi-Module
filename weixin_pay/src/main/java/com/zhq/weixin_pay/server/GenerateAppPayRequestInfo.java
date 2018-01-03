package com.zhq.weixin_pay.server;

import com.zhq.weixin_pay.client.PayRequestBean;

import org.json.JSONObject;

/**
 * 类的描述: 生成app支付需要的参数信息
 */
public class GenerateAppPayRequestInfo {

    public static PayRequestBean generateAppPayRequest() {


        return new PayRequestBean();
    }
}
