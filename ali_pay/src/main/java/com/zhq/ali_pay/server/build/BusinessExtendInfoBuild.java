package com.zhq.ali_pay.server.build;


import com.zhq.ali_pay.server.bean.BusinessExtendInfoBean;

/**
 * 类的描述: 订单信息的业务扩展信息Bean类 的建造者
 */
public class BusinessExtendInfoBuild extends AliPayBeanBuild {

    private final BusinessExtendInfoBean mBusinessExtendInfo;

    public BusinessExtendInfoBuild() {
        mBusinessExtendInfo = new BusinessExtendInfoBean();
    }

    /**
     * 方法的描述: 不必填	最大长度:64	系统商编号，该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID	2088511833207846
     */
    public BusinessExtendInfoBuild sys_service_provider_id(String sys_service_provider_id) throws Throwable {
        if (judgeParameterLength(64, sys_service_provider_id.length()) && verifyParameterContent(sys_service_provider_id)) {
            mBusinessExtendInfo.sys_service_provider_id = sys_service_provider_id;
            return this;
        } else {
            throw new Throwable("sys_service_provider_id length overstep or include ali pay key");
        }
    }

    /**
     * 方法的描述: 不必填	最大长度:1	是否发起实名校验 T：发起 F：不发起
     */
    public BusinessExtendInfoBuild needBuyerRealnamed(String needBuyerRealnamed) throws Throwable {
        if (judgeParameterLength(1, needBuyerRealnamed.length()) && verifyParameterContent(needBuyerRealnamed)) {
            mBusinessExtendInfo.needBuyerRealnamed = needBuyerRealnamed;
            return this;
        } else {
            throw new Throwable("needBuyerRealnamed length overstep or include ali pay key");
        }
    }

    /**
     * 方法的描述: 不必填	最大长度:128	账务备注 注：该字段显示在离线账单的账务备注中	促销
     */
    public BusinessExtendInfoBuild TRANS_MEMO(String TRANS_MEMO) throws Throwable {
        if (judgeParameterLength(128, TRANS_MEMO.length()) && verifyParameterContent(TRANS_MEMO)) {
            mBusinessExtendInfo.TRANS_MEMO = TRANS_MEMO;
            return this;
        } else {
            throw new Throwable("TRANS_MEMO length overstep or include ali pay key");
        }
    }

    /**
     * 方法的描述: 不必填	最大长度:5	花呗分期数（目前仅支持3、6、12）注：使用该参数需要仔细阅读“花呗分期接入文档”
     */
    public BusinessExtendInfoBuild hb_fq_num(String hb_fq_num) throws Throwable {
        if (judgeParameterLength(5, hb_fq_num.length()) && verifyParameterContent(hb_fq_num)) {
            mBusinessExtendInfo.hb_fq_num = hb_fq_num;
            return this;
        } else {
            throw new Throwable("hb_fq_num length overstep or include ali pay key");
        }
    }

    /**
     * 方法的描述: 不必填	最大长度:3	卖家承担收费比例，商家承担手续费传入100，用户承担手续费传入0，仅支持传入100、0两种，其他比例暂不支持 注：使用该参数需要仔细阅读“花呗分期接入文档”	100
     */
    public BusinessExtendInfoBuild hb_fq_seller_percent(String hb_fq_seller_percent) throws Throwable {
        if (judgeParameterLength(3, hb_fq_seller_percent.length()) && verifyParameterContent(hb_fq_seller_percent)) {
            mBusinessExtendInfo.hb_fq_seller_percent = hb_fq_seller_percent;
            return this;
        } else {
            throw new Throwable("hb_fq_seller_percent length overstep or include ali pay key");
        }
    }

    public BusinessExtendInfoBean buildBusinessExtendInfo() {
        return mBusinessExtendInfo;
    }
}
