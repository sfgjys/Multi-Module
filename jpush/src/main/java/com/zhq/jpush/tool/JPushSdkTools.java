package com.zhq.jpush.tool;

import android.content.Context;
import android.text.TextUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;

/**
 * 封装了极光推送SDK中的方法
 */
public class JPushSdkTools {

    /**
     * 方法描述: 获取极光推送SDK 向 极光推送服务器 注册所得到的 注册 全局唯一的 ID----该方法是通过SDK获取RegistrationID，还有一种是注册成功后通过自定义广播接收的RegistrationID
     */
    public static String getRegistrationID(Context context) {
        // 因为RegistrationID需要通过广播获取，所以需要一定时间，getRegistrationID方法的调用要有延时
        String registrationID = JPushInterface.getRegistrationID(context);
        return TextUtils.isEmpty(registrationID) ? "" : registrationID;
    }

    /**
     * 方法描述: 通过极光推送的SDK来获取设备唯一ID？？？TODO 有待考证
     */
    public static String getDeviceId(Context context) {
        return JPushInterface.getUdid(context);
    }

    /**
     * 方法描述: 开关极光推送服务，JPush SDK 提供的推送服务是默认开启的(也就是说init初始化后就已经开启了极光推送的服务)。
     * 本功能其行为类似于网络中断的效果，即：推送服务停止期间，不接收推送的消息，
     * 恢复推送服务后，如果推送的消息还在保留的时长范围内，则客户端是会收到离线消息。
     *
     * @param context 应用的 ApplicationContext
     */
    public static void openOrCloseJPushServer(Context context) {
        if (JPushInterface.isPushStopped(context)) {// 用来检查 Push Service 是否已经被停止
            JPushInterface.resumePush(context);// 调用了此 API 后，极光推送完全恢复正常工作。
        } else {
            JPushInterface.stopPush(context);// 极光推送所有的其他 API 调用都无效,不能通过 JPushInterface.init 恢复，需要调用resumePush恢复
        }
    }

    /**
     * 方法描述: 通过极光推送的SDK，将参数中有效的标签筛选出来
     */
    public static Set<String> filterValidTags(Set<String> tags) {
        return JPushInterface.filterValidTags(tags);
    }

    /**
     * 方法描述: 当Activity走到了获取了焦点的生命周期的时候，通过极光推送的SDK，去记录Activity界面获取了焦点的次数
     */
    public static void recordActivityOnResume(Context context) {
        JPushInterface.onResume(context);
    }

    /**
     * 方法描述: 当Activity走到了失去了焦点的生命周期的时候，通过极光推送的SDK，去记录Activity界面获取了焦点的次数
     */
    public static void recordActivityOnPause(Context context) {
        JPushInterface.onPause(context);
    }

    /**
     * 方法描述: 当用户点击并打开极光推送发送过来的通知，或者使用极光推送传递过来的自定义通知展示信息的时候，我们可以调用本方法记录用户查看了参数二对应的通知消息
     *
     * @param messageID messageID 来源于发送消息和通知的 Extra 字段 JPushInterface.EXTRA_MSG_ID，参考 接收推送消息Receiver
     * @param context   应用的 ApplicationContext
     */
    public static void recordJPUSHNotificationOpened(Context context, String messageID) {
        JPushInterface.reportNotificationOpened(context, messageID);
    }

    /**
     * 方法描述: 清除所有 JPush 展现的通知（不包括非 JPush SDK 展现的）；或者清除指定某个通知。
     *
     * @param context        应用的 ApplicationContext
     * @param notificationId 来源于intent参数 JPushInterface.EXTRA_NOTIFICATION_ID，-1 的时候代表清除所有通知栏中的极光推送的通知，
     */
    public static void clearJPUSHNotification(Context context, int notificationId) {
        if (notificationId == -1) {
            JPushInterface.clearAllNotifications(context);
        } else {
            JPushInterface.clearNotificationById(context, notificationId);
        }
    }

    /**
     * 方法描述: 设置什么时间段接收极光推送。如果不在该时间段内收到消息，SDK的行为是：推送到的通知会被扔掉。注意： 这是一个纯粹客户端的实现。所以与客户端时间是否准确、时区等这些，都没有关系。
     *
     * @param context   应用的ApplicationContext
     * @param startHour 参数weekDays中的每一天中允许推送的开始时间 （24小时制：startHour的范围为0到23）
     * @param endHour   参数weekDays中的每一天中允许推送的结束时间 （24小时制：endHour的范围为0到23）
     * @param weekDays  0表示星期天，1表示星期一，以此类推。 （7天制，Set集合里面的int范围为0到6）。
     *                  如果传入的集合为null，则任何时间都可以收到消息和通知，如果传入的集合大小为0，size为0，则表示任何时间都收不到消息和通知.
     */
    public static void setWhatTimePeriodReceiveJPUSH(Context context, Set<Integer> weekDays, int startHour, int endHour) {
        JPushInterface.setPushTime(context, weekDays, startHour, endHour);
    }

    /**
     * 方法描述: 设置什么时间段接收极光推送的通知是静音状态，不会有铃声和震动
     *
     * @param context     应用的ApplicationContext
     * @param startHour   静音时段的开始时间 - 小时 （24小时制，范围：0~23 ）
     * @param startMinute 静音时段的开始时间 - 分钟（范围：0~59 ）
     * @param endHour     静音时段的结束时间 - 小时 （24小时制，范围：0~23 ）
     * @param endMinute   静音时段的结束时间 - 分钟（范围：0~59 ）
     */
    public static void setWhatTimePeriodNotificationSilence(Context context, int startHour, int startMinute, int endHour, int endMinute) {
        JPushInterface.setSilenceTime(context, startHour, startMinute, endHour, endMinute);
    }

    /**
     * 方法描述: 设置通知栏中可以显示的最多的极光推送推送过来的通知个数，可以在 JPushInterface.init 之后任何地方调用。可以调用多次。SDK使用最后调用的数值。
     *
     * @param context 应用的 ApplicationContext
     * @param maxNum  最多显示的条数
     */
    public static void setCanShowJPUSHNotificationNumber(Context context, int maxNum) {
        JPushInterface.setLatestNotificationNumber(context, maxNum);
    }


    /**
     * 方法描述: 使用极光推送的SDK添加一个定时发送的本地通知，本接口可以在 JPushInterface.init 之后任何地方调用
     * 注意本地通知有如下三个特性:  本地通知API不依赖于网络，无网条件下依旧可以触发
     *                              本地通知与网络推送的通知是相互独立的，不受保留最近通知条数上限的限制
     *                              本地通知的定时时间是自发送时算起的，不受中间关机等操作的影响
     *
     * @param context           是应用的 ApplicationContext
     * @param localNotification 是用极光推送SDK创建的本地通知对象
     */
    public static void addJPUSHLocalNotification(Context context, JPushLocalNotification localNotification) {
        JPushInterface.addLocalNotification(context, localNotification);
    }

    /**
     * 方法描述: 根据参数二去移除本地通知，本接口可以在 JPushInterface.init 之后任何地方调用
     *
     * @param context        是应用的 ApplicationContext
     * @param notificationId 是要移除的本地通知的ID
     */
    public static void removeJPUSHLocalNotification(Context context, long notificationId) {
        JPushInterface.removeLocalNotification(context, notificationId);
    }

    /**
     * 方法描述: 清除所有存在的极光推送的本地通知，本接口可以在 JPushInterface.init 之后任何地方调用
     *
     * @param context 是应用的 ApplicationContext
     */
    public static void clearJPUSHLocalNotifications(Context context) {
        JPushInterface.clearLocalNotifications(context);
    }
}
