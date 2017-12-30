package com.zhq.ali_pay;

import java.util.HashMap;
import java.util.Map;

/**
 * 类的描述: 支付宝支付-对订单信息进行处理的工具类
 */
public class OrderFormInfoTool {

    /**
     * 方法描述: 将订单公共信息参数转换为Map集合存储
     */
    public static void orderFormPublicInfoShiftMap() {
        Map<String, String> orderFormInfo = new HashMap<>();
        // 支付宝分配给开发者的应用ID
        orderFormInfo.put("", AliPayFieldsConfig.TEST_APP_ID);// 服务端
        // 接口名称
        orderFormInfo.put("method", "alipay.trade.app.pay");// 服务端
        // 目前仅支持JSON
        orderFormInfo.put("format", "JSON");// 服务端
        // 请求使用的编码格式，如utf-8,gbk,gb2312等，但是恒定utf-8
        orderFormInfo.put("charset", "utf-8");// 服务端
        // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
        orderFormInfo.put("sign_type", "RSA2");// 服务端
        //
        orderFormInfo.put("sign", "");// TODO????????????
        // 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
        orderFormInfo.put("timestamp", "2016-07-29 16:55:53");// 客户端
        // 调用的接口版本，固定为：1.0
        orderFormInfo.put("version", "1.0");// 服务端
        //
        orderFormInfo.put("notify_url", "");// TODO????????????
        // 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
        orderFormInfo.put("biz_content", "");// TODO????????????
    }

    public void orderFormBusinessInfoShiftMap() {

    }


}
