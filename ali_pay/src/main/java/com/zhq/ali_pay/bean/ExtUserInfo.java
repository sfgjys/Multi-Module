package com.zhq.ali_pay.bean;

/**
 * 类的描述: 外部指定买家, TODO 支付宝找人代付功能需要的参数对象
 * <p>注意: 类名不可以更改</p>
 */
public class ExtUserInfo {

    /**
     * 变量的描述: 不必填  最大长度:16   姓名 注： need_check_info=T时该参数才有效
     */
    private String name;
    /**
     * 变量的描述: 不必填  最大长度:20   手机号 注：该参数暂不校验
     */
    private String mobile;
    /**
     * 变量的描述: 不必填  最大长度:32  注： need_check_info=T时该参数才有效
     * <p>身份证：IDENTITY_CARD、</p>
     * <p>护照：PASSPORT、</p>
     * <p>军官证：OFFICER_CARD、</p>
     * <p>士兵证：SOLDIER_CARD、</p>
     * <p>户口本：HOKOU等。</p>
     * 如有其它类型需要支持，请与蚂蚁金服工作人员联系。
     */
    private String cert_type;
    /**
     * 变量的描述: 不必填  最大长度:64  证件号  注：need_check_info=T时该参数才有效
     */
    private String cert_no;
    /**
     * 变量的描述: 不必填  最大长度:3  允许的最小买家年龄，买家年龄必须大于等于所传数值
     * <p>注：</p>
     * <p>1. need_check_info=T时该参数才有效</p>
     * <p>2. min_age为整数，必须大于等于0</p>
     */
    private String min_age;
    /**
     * 变量的描述: 不必填  最大长度:8  是否强制校验付款人身份信息  T:强制校验，F：不强制
     */
    private String fix_buyer;
    /**
     * 变量的描述: 不必填  最大长度:1  是否强制校验身份信息 T:强制校验，F：不强制
     */
    private String need_check_info;

}
