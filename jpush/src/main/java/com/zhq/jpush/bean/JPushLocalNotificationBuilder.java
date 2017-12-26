package com.zhq.jpush.bean;

import java.util.Date;

import cn.jpush.android.data.JPushLocalNotification;

public class JPushLocalNotificationBuilder {

    private final JPushLocalNotification mJPushLocalNotification;

    public JPushLocalNotification getJPushLocalNotification() {
        return mJPushLocalNotification;
    }

    public JPushLocalNotificationBuilder() {
        mJPushLocalNotification = new JPushLocalNotification();
    }

    /**
     * 方法描述: 设置本地通知触发时间，该触发时间是以Date对象来设置的
     */
    public JPushLocalNotificationBuilder setBroadcastTime(Date date) {
        mJPushLocalNotification.setBroadcastTime(date);
        return this;
    }
    /**
     * 方法描述: 设置本地通知触发时间，该触发时间是以时间戳来设置的
     */
    public JPushLocalNotificationBuilder setBroadcastTime(long l) {
        mJPushLocalNotification.setBroadcastTime(l);
        return this;
    }
    /**
     * 方法描述: 设置本地通知触发时间，TODO  该触发时间是以什么来设置的
     */
    public JPushLocalNotificationBuilder setBroadcastTime(int i1, int i2, int i3, int i4, int i5, int i6) {
        mJPushLocalNotification.setBroadcastTime(i1, i2, i3, i4, i5, i6);
        return this;
    }
    /**
     * 方法描述: 设置本地通知样式 TODO ????????????
     */
    public JPushLocalNotificationBuilder setBuilderId(long l) {
        mJPushLocalNotification.setBuilderId(l);
        return this;
    }
    /**
     * 方法描述: 设置本地通知的content
     */
    public JPushLocalNotificationBuilder setContent(String content) {
        mJPushLocalNotification.setContent(content);
        return this;
    }
    /**
     * 方法描述: 设置额外的数据信息extras为json字符串
     */
    public JPushLocalNotificationBuilder setExtras(String extras) {
        mJPushLocalNotification.setExtras(extras);
        return this;
    }
    /**
     * 方法描述: 设置本地通知的ID
     */
    public JPushLocalNotificationBuilder setNotificationId(long notificationId) {
        mJPushLocalNotification.setNotificationId(notificationId);
        return this;
    }
    /**
     * 方法描述: 设置本地通知的title
     */
    public JPushLocalNotificationBuilder setTitle(String title) {
        mJPushLocalNotification.setTitle(title);
        return this;
    }
}
