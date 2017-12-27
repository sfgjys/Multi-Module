package com.zhq.jpush.bean;

import java.util.Set;

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

    private int mWhichAction;
    private Set<String> mTagsData;
    private String mAliasData;
    private boolean mIsAliasAction;// 是否是别名操作动作

    public AliasTagsBean(int mWhichAction, Set<String> mTagsData, String mAliasData, boolean mIsAliasAction) {
        this.mWhichAction = mWhichAction;
        this.mTagsData = mTagsData;
        this.mAliasData = mAliasData;
        this.mIsAliasAction = mIsAliasAction;
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

    @Override
    public String toString() {
        return "AliasTagsBean{" +
                "mWhichAction=" + mWhichAction +
                ", mTagsData=" + mTagsData +
                ", mAliasData='" + mAliasData + '\'' +
                ", mIsAliasAction=" + mIsAliasAction +
                '}';
    }
}
