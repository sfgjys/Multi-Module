package com.zhq.ali_pay.server.build;


import com.zhq.ali_pay.server.bean.ExtUserInfo;

/**
 * 类的描述: 外部指定买家Bean类的建造者
 */
public class ExtUserInfoBuild extends AliPayBeanBuild {

    private final ExtUserInfo mExtUserInfo;

    public ExtUserInfoBuild() {
        mExtUserInfo = new ExtUserInfo();
    }

    /**
     * 方法的描述: 不必填  最大长度:16   姓名 注： need_check_info=T时该参数才有效
     */
    public ExtUserInfoBuild name(String name) throws Throwable {
        if (judgeParameterLength(16, name.length()) && verifyParameterContent(name)) {
            mExtUserInfo.name = name;
            return this;
        } else {
            throw new Throwable("name length overstep or include ali pay key");
        }
    }
    /**
     * 方法的描述: 不必填  最大长度:20   手机号 注：该参数暂不校验
     */
    public ExtUserInfoBuild mobile(String mobile) throws Throwable {
        if (judgeParameterLength(20, mobile.length()) && verifyParameterContent(mobile)) {
            mExtUserInfo.mobile = mobile;
            return this;
        } else {
            throw new Throwable("mobile length overstep or include ali pay key");
        }
    }
    /**
     * 方法的描述: 不必填  最大长度:32  注： need_check_info=T时该参数才有效
     * <p>身份证：IDENTITY_CARD、</p>
     * <p>护照：PASSPORT、</p>
     * <p>军官证：OFFICER_CARD、</p>
     * <p>士兵证：SOLDIER_CARD、</p>
     * <p>户口本：HOKOU等。</p>
     * 如有其它类型需要支持，请与蚂蚁金服工作人员联系。
     */
    public ExtUserInfoBuild cert_type(String cert_type) throws Throwable {
        if (judgeParameterLength(32, cert_type.length()) && verifyParameterContent(cert_type)) {
            mExtUserInfo.cert_type = cert_type;
            return this;
        } else {
            throw new Throwable("cert_type length overstep or include ali pay key");
        }
    }
    /**
     * 方法的描述: 不必填  最大长度:64  证件号  注：need_check_info=T时该参数才有效
     */
    public ExtUserInfoBuild cert_no(String cert_no) throws Throwable {
        if (judgeParameterLength(64, cert_no.length()) && verifyParameterContent(cert_no)) {
            mExtUserInfo.cert_no = cert_no;
            return this;
        } else {
            throw new Throwable("cert_no length overstep or include ali pay key");
        }
    }
    /**
     * 方法的描述: 不必填  最大长度:3  允许的最小买家年龄，买家年龄必须大于等于所传数值
     * <p>注：</p>
     * <p>1. need_check_info=T时该参数才有效</p>
     * <p>2. min_age为整数，必须大于等于0</p>
     */
    public ExtUserInfoBuild min_age(String min_age) throws Throwable {
        if (judgeParameterLength(3, min_age.length()) && verifyParameterContent(min_age)) {
            mExtUserInfo.min_age = min_age;
            return this;
        } else {
            throw new Throwable("min_age length overstep or include ali pay key");
        }
    }
    /**
     * 方法的描述: 不必填  最大长度:8  是否强制校验付款人身份信息  T:强制校验，F：不强制
     */
    public ExtUserInfoBuild fix_buyer(String fix_buyer) throws Throwable {
        if (judgeParameterLength(8, fix_buyer.length()) && verifyParameterContent(fix_buyer)) {
            mExtUserInfo.fix_buyer = fix_buyer;
            return this;
        } else {
            throw new Throwable("fix_buyer length overstep or include ali pay key");
        }
    }
    /**
     * 方法的描述: 不必填  最大长度:1  是否强制校验身份信息 T:强制校验，F：不强制
     */
    public ExtUserInfoBuild need_check_info(String need_check_info) throws Throwable {
        if (judgeParameterLength(1, need_check_info.length()) && verifyParameterContent(need_check_info)) {
            mExtUserInfo.need_check_info = need_check_info;
            return this;
        } else {
            throw new Throwable("need_check_info length overstep or include ali pay key");
        }
    }

    public ExtUserInfo buildExtUserInfo() {
        return mExtUserInfo;
    }
}
