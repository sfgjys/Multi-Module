package com.zhq.ali_pay.server.bean;

/**
 * 类的描述: 订单业务信息Bean类
 */
public class OrderFormBusinessInfoBean {
    /**
     * 变量的描述: 必填	最大长度:256	商品的标题/交易标题/订单标题/订单关键字等。  大乐透
     */
    public String subject;
    /**
     * 变量的描述: 必填	最大长度:64	商户网站唯一订单号	70501111111S001111119
     */
    public String out_trade_no;
    /**
     * 变量的描述: 必填	最大长度:9	订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]	9.00
     */
    public String total_amount;
    /**
     * 变量的描述: 必填	最大长度:64	销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY	QUICK_MSECURITY_PAY
     */
    public String product_code;

    /**
     * 变量的描述: 不必填 最大长度:128	对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 Iphone6 16G
     */
    public String body;
    /**
     * 变量的描述: 不必填	最大长度:6	该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。注：若为空，则默认为15d。	90m
     */
    public String timeout_express;
    /**
     * 变量的描述: 不必填	最大长度:2	商品主类型：0—虚拟类商品，1—实物类商品 注：虚拟类商品不支持使用花呗渠道	0
     */
    public String goods_type;
    /**
     * 变量的描述: 不必填	最大长度:512	公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝	merchantBizType%3d3C%26merchantBizNo%3d2016010101111
     */
    public String passback_params;
    /**
     * 变量的描述: 不必填	最大长度:512	优惠参数 注：仅与支付宝协商后可用	{"storeIdType":"1"}
     */
    public String promo_params;
    /**
     * 变量的描述: 不必填	最大长度:32	商户门店编号。该参数用于请求参数中以区分各门店，非必传项。	NJ_001
     */
    public String store_id;


    /**
     * 变量的描述: 不必填	业务扩展参数，详见下面的“业务扩展参数说明”	{"sys_service_provider_id":"2088511833207846"}
     */
    public String extend_params;
    /**
     * 变量的描述: 不必填；最大长度:128	；用户只能在指定渠道范围内支付；当有多个渠道时用“,”分隔；注：与disable_pay_channels字段互斥；例如:pcredit,moneyFund,debitCardExpress
     * <p>balance	余额</p>
     * <p>moneyFund	余额宝</p>
     * <p>coupon	红包</p>
     * <p>pcredit	花呗</p>
     * <p>pcreditpayInstallment	花呗分期</p>
     * <p>creditCard	信用卡</p>
     * <p>creditCardExpress	信用卡快捷</p>
     * <p>creditCardCartoon	信用卡卡通</p>
     * <p>credit_group	信用支付类型（包含信用卡卡通、信用卡快捷、花呗、花呗分期）</p>
     * <p>debitCardExpress	借记卡快捷</p>
     * <p>mcard	商户预存卡</p>
     * <p>pcard	个人预存卡</p>
     * <p>promotion	优惠（包含实时优惠+商户优惠</p>
     * <p>voucher	营销券</p>
     * <p>point	积分</p>
     * <p>mdiscount	商户优惠</p>
     * <p>bankPay	网银</p>
     */
    public String enable_pay_channels;
    // 不必填		外部指定买家，详见外部用户ExtUserInfo参数说明
    public ExtUserInfo ext_user_info;
}
