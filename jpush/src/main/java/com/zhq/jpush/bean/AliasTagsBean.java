package com.zhq.jpush.bean;

import java.util.Set;

import cn.jpush.android.api.JPushMessage;

/**
 * 类的描述: 操作别名或者标签时需要的参数
 */
public class AliasTagsBean {
    // 别名的操作分类
    public static final int ALIAS_ACTION_SET = 0;
    public static final int ALIAS_ACTION_GET = 1;
    public static final int ALIAS_ACTION_DELETE = 2;
    // 标签的操作分类
    public static final int TAG_ACTION_SET = 3;
    public static final int TAG_ACTION_ADD = 4;
    public static final int TAG_ACTION_DELETE = 5;
    public static final int TAG_ACTION_CLEAN_ALL = 6;
    public static final int TAG_ACTION_GET_ALL = 7;
    public static final int TAG_ACTION_CHECK = 8;

    private int mWhichAction;// 操作动作类型
    private Set<String> mTagsData;// 标签操作需要的数据
    private String mAliasData;// 别名操作需要的数据
    private boolean mIsAliasAction;// 是否是别名操作动作
    private String mActionName;// 操作动作类型的名字
    private JPushMessage mActionResult;// 操作结果
    private boolean isNeedAgainAction = false;// 是否需要尝试重新操作

    public AliasTagsBean(int mWhichAction, Set<String> mTagsData, String mAliasData, boolean mIsAliasAction) {
        this.mWhichAction = mWhichAction;
        this.mTagsData = mTagsData;
        this.mAliasData = mAliasData;
        this.mIsAliasAction = mIsAliasAction;
        this.mActionName = getActionString(mWhichAction);
    }

    public AliasTagsBean() {
    }

    public int getWhichAction() {
        return mWhichAction;
    }

    public void setWhichAction(int mWhichAction) {
        this.mWhichAction = mWhichAction;
    }

    public Set<String> getTagsData() {
        return mTagsData;
    }

    public void setTagsData(Set<String> mTagsData) {
        this.mTagsData = mTagsData;
    }

    public String getAliasData() {
        return mAliasData;
    }

    public void setAliasData(String mAliasData) {
        this.mAliasData = mAliasData;
    }

    public boolean isAliasAction() {
        return mIsAliasAction;
    }

    public void setIsAliasAction(boolean mIsAliasAction) {
        this.mIsAliasAction = mIsAliasAction;
    }

    public String getActionName() {
        return mActionName;
    }

    public void setActionName(String mActionName) {
        this.mActionName = mActionName;
    }

    public JPushMessage getActionResult() {
        return mActionResult;
    }

    public void setActionResult(JPushMessage mActionResult) {
        this.mActionResult = mActionResult;
    }

    public boolean isNeedAgainAction() {
        return isNeedAgainAction;
    }

    public void setNeedAgainAction(boolean needAgainAction) {
        isNeedAgainAction = needAgainAction;
    }

    private String getActionString(int whichAction) {
        switch (whichAction) {
            // 别名操作
            case AliasTagsBean.ALIAS_ACTION_DELETE:
                return "删除绑定中的别名";
            case AliasTagsBean.ALIAS_ACTION_GET:
                return "获取绑定中的别名";
            case AliasTagsBean.ALIAS_ACTION_SET:
                return "设置要进行绑定的别名";
            // 标签操作
            case AliasTagsBean.TAG_ACTION_ADD:
                return "在原有的基础上添加多个标签";
            case AliasTagsBean.TAG_ACTION_CHECK:
                return "查询指定一个标签与用户绑定状态";
            case AliasTagsBean.TAG_ACTION_CLEAN_ALL:
                return "清除所有标签";
            case AliasTagsBean.TAG_ACTION_DELETE:
                return "删除多个标签";
            case AliasTagsBean.TAG_ACTION_GET_ALL:
                return "查询所有标签";
            case AliasTagsBean.TAG_ACTION_SET:
                return "设置并覆盖掉原有的标签";
        }
        return "没有定义的操作";
    }

    @Override
    public String toString() {
        return "AliasTagsBean{" +
                "mWhichAction=" + mWhichAction +
                ", mTagsData=" + mTagsData +
                ", mAliasData='" + mAliasData + '\'' +
                ", mIsAliasAction=" + mIsAliasAction +
                ", mActionName='" + mActionName + '\'' +
                ", mActionResult=" + mActionResult +
                ", isNeedAgainAction=" + isNeedAgainAction +
                '}';
    }
}
