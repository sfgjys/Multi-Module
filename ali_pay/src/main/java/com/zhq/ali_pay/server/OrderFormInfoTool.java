package com.zhq.ali_pay.server;

import com.zhq.ali_pay.server.sign.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述: 支付宝支付-对订单信息进行处理的工具类
 */
public class OrderFormInfoTool {

    /**
     * 方法描述: 将订单公共信息参数转换为Map集合存储
     *
     * @param currentTimeMillis 发送支付请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     */
    public static Map<String, String> getOrderFormPublicInfoMap(String currentTimeMillis) {
        Map<String, String> orderFormInfo = new HashMap<>();
        // 接口名称
        orderFormInfo.put("method", "alipay.trade.app.pay");// 服务端
        // 目前仅支持JSON
        orderFormInfo.put("format", "JSON");// 服务端
        // 请求使用的编码格式，如utf-8,gbk,gb2312等，但是恒定utf-8
        orderFormInfo.put("charset", "utf-8");// 服务端
        // 调用的接口版本，固定为：1.0
        orderFormInfo.put("version", "1.0");// 服务端
        // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
        orderFormInfo.put("sign_type", AliPayFieldsConfig.SIGN_TYPE);// 服务端
        // 支付宝分配给开发者的应用ID
        orderFormInfo.put("app_id", AliPayFieldsConfig.APP_ID);// 服务端
        // 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
        orderFormInfo.put("notify_url", AliPayFieldsConfig.PAY_RESULT_NOTIFY_CALLBACK_URL);// 服务端

        // 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
        orderFormInfo.put("timestamp", currentTimeMillis);// 客户端

        // 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
        orderFormInfo.put("biz_content", "");// TODO????????????


        // TODO orderFormInfo.put("sign", ""); 签名的值不在这里赋值
        return orderFormInfo;
    }

    /**
     * 方法描述: 将拼接过的所有键值对通过 "&" 来拼接，进而获取除了“sign”参数的所有支付请求参数拼接完成的字符串
     */
    public static String jointAllPayParameterExceptSign(Map<String, String> map) {
        // 获取所有所有键值对的Key
        List<String> keys = new ArrayList<>(map.keySet());

        StringBuilder stringBuilder = new StringBuilder();
        // 这里获取了除了最后一对的所有键值对
        for (int i = 0; i < keys.size() - 1; i++) {
            // 从集合中获取Key
            String key = keys.get(i);
            // 获取Key对应的Value
            String value = map.get(key);
            // 将Key和Value先进行拼接，在把拼接结果作为整体在拼接
            stringBuilder.append(jointKeyValue(key, value, true));
            stringBuilder.append("&");
        }

        // 获取最后一对键值对
        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        // 最后拼接
        stringBuilder.append(jointKeyValue(tailKey, tailValue, true));
        return stringBuilder.toString();
    }

    /**
     * 方法描述: 以"="来拼接键值对，并且根据参数来确定是否对value进行编码
     */
    private static String jointKeyValue(String key, String value, boolean isEncode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key);// 先添加Key
        stringBuilder.append("=");// 在添加"="
        if (isEncode) {
            try {
                // 对value进行编码后在拼接
                stringBuilder.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                stringBuilder.append(value);
            }
        } else {
            stringBuilder.append(value);
        }
        return stringBuilder.toString();
    }

    /**
     * 方法描述: 给已经拼接的所有支付参数(除了sign)进行加密，加密方法是参数三，加密密钥是参数二
     *
     * @return 获取的是“sign=加密结果”
     */
    public static String encryptionAllPayParameterExceptSign(String allPayParameterExceptSign, String privateKey, boolean isRSA2) {
        String signResult = SignUtils.sign(allPayParameterExceptSign, privateKey, isRSA2);
        String encodedSignResult = "";
        try {
            // 给加密结果进行编码
            encodedSignResult = URLEncoder.encode(signResult, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "sign=" + encodedSignResult;
    }

    /**
     * 方法描述: 获取最终支付用的订单信息String
     */
    public static String getUltimatelyPayInfoString(Map<String, String> map) {
        String unSignPayInfo = jointAllPayParameterExceptSign(map);
        String signPayInfo = encryptionAllPayParameterExceptSign(unSignPayInfo, AliPayFieldsConfig.RSA2_PRIVATE, true);
        return unSignPayInfo + "&" + signPayInfo;
    }
}
