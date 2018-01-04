package com.zhq.weixin_pay.server;

public class PayRequestBean {

    /**
     * 变量的描述: 微信开放平台审核通过的应用APPID
     */
    public String appId;
    /**
     * 变量的描述: 微信支付分配的商户号
     */
    public String partnerId;
    /**
     * 变量的描述: 暂填写固定值Sign=WXPay
     */
    public String packageValue;
    /**
     * 变量的描述: 时间戳  部分系统取到的值为毫秒级，需要转换成秒(10位数字),所以截取字符串前10位
     */
    public String timeStamp;
    /**
     * 变量的描述: 预支付交易会话ID
     */
    public String prepayId;
    /**
     * 变量的描述: 随机字符串
     */
    public String nonceStr;
    /**
     * 变量的描述: 将上面的变量拼接后进行签名的结果
     */
    public String sign;
}
