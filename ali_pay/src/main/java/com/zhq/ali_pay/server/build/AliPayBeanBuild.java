package com.zhq.ali_pay.server.build;

import java.util.HashMap;

/**
 * 类的描述: 支付宝支付时需要的参数对象建造者的总父类
 */
public class AliPayBeanBuild {

    private final static String[] mAliPayAllKeys = {
            "app_id", "method", "format", "charset", "sign_type", "sign", "timestamp", "version", "notify_url", "biz_content",
            "body", "subject", "out_trade_no", "timeout_express", "total_amount", "product_code",
            "goods_type", "passback_params", "promo_params", "extend_params", "enable_pay_channels",
            "disable_pay_channels", "store_id", "ext_user_info", "sys_service_provider_id", "needBuyerRealnamed",
            "TRANS_MEMO", "hb_fq_num", "hb_fq_seller_percent", "name", "mobile", "cert_type", "cert_no", "min_age",
            "fix_buyer", "need_check_info"
    };
    private static HashMap<String, Integer> mSaveAliPayAllKeys = null;

    /**
     * 方法描述: 判断参数二是否超出参数一
     */
    boolean judgeParameterLength(int maxLength, int practicalLength) {
        return practicalLength <= maxLength;
    }

    /**
     * 方法描述: 验证参数中的内容是否包含mAliPayAllKeys数组中的某个字段
     */
    boolean verifyParameterContent(String needVerifyParameter) {
        if (mSaveAliPayAllKeys == null) {
            mSaveAliPayAllKeys = new HashMap<>();
            for (String aliPayKey : mAliPayAllKeys) {
                mSaveAliPayAllKeys.put(aliPayKey, 9);
            }
        }
        Integer integer = mSaveAliPayAllKeys.get(needVerifyParameter);
        return integer == null || integer != 9;
    }
}
