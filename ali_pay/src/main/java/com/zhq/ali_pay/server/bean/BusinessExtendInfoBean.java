package com.zhq.ali_pay.server.bean;

/**
 * 类的描述: 订单信息的业务扩展信息Bean类
 */
public class BusinessExtendInfoBean {

    /**
     * 变量的描述: 不必填	最大长度:64	系统商编号，该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID	2088511833207846
     */
    public String sys_service_provider_id;
    /**
     * 变量的描述: 不必填	最大长度:1	是否发起实名校验 T：发起 F：不发起
     */
    public String needBuyerRealnamed;
    /**
     * 变量的描述: 不必填	最大长度:128	账务备注 注：该字段显示在离线账单的账务备注中	促销
     */
    public String TRANS_MEMO;
    /**
     * 变量的描述: 不必填	最大长度:5	花呗分期数（目前仅支持3、6、12）注：使用该参数需要仔细阅读“花呗分期接入文档”
     */
    public String hb_fq_num;
    /**
     * 变量的描述: 不必填	最大长度:3	卖家承担收费比例，商家承担手续费传入100，用户承担手续费传入0，仅支持传入100、0两种，其他比例暂不支持 注：使用该参数需要仔细阅读“花呗分期接入文档”	100
     */
    public String hb_fq_seller_percent;
}
