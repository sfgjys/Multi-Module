package com.zhq.weixin_pay.server;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;

/**
 * 类的描述: 生成app支付需要的参数信息
 */
public class GenerateAppPayRequestInfo {

    /**
     * 方法描述: 将微信请求支付的信息存储进PayRequestBean对象中,然后通过反射获取PayRequestBean对象中的数据然后将数据转换为JSON字符串
     */
    public static String generateAppPayRequest(String prepayId) {
        PayRequestBean payRequestBean = new PayRequestBean();
        payRequestBean.appId = WeiXinPayFieldsConfig.APP_ID;
        payRequestBean.partnerId = WeiXinPayFieldsConfig.PARTENER_ID;
        payRequestBean.packageValue = "Sign=WXPay";
        payRequestBean.timeStamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        payRequestBean.prepayId = prepayId;
        payRequestBean.nonceStr = GenerateAppPayRequestInfo.encryptionMD5(String.valueOf(new Random().nextInt(10000)).getBytes());
        String beEncrypted = "appid=" + payRequestBean.appId + "&noncestr=" + payRequestBean.nonceStr + "&package=" + payRequestBean.packageValue +
                "&partnerid=" + payRequestBean.partnerId + "&prepayid=" + payRequestBean.prepayId + "&timestamp="
                + payRequestBean.timeStamp + "&key=" + WeiXinPayFieldsConfig.KEY;
        String alreadyEncrypted = GenerateAppPayRequestInfo.encryptionMD5(beEncrypted.getBytes());
        if (alreadyEncrypted != null) {
            payRequestBean.sign = alreadyEncrypted.toUpperCase(Locale.getDefault());
        }
        return switchPayRequestBeanToJson(payRequestBean);
    }

    /**
     * 方法描述: 通过反射获取PayRequestBean对象中的数据然后将数据转换为JSON字符串
     */
    private static String switchPayRequestBeanToJson(PayRequestBean payRequestBean) {
        Field[] fields = payRequestBean.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        try {
            for (int i = 0; i < (fields.length - 1); i++) {
                stringBuilder.append("\"").append(fields[i].getName()).append("\":\"").append((String) fields[i].get(payRequestBean)).append("\",");
            }
            stringBuilder.append("\"").append(fields[fields.length - 1].getName()).append("\":\"").append((String) fields[fields.length - 1].get(payRequestBean)).append("\"");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }


    private static String encryptionMD5(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
