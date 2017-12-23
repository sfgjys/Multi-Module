package com.zhq.jpush.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jpush.android.api.JPushInterface;

public class JPUSHTools {
    public static final String PREFS_NAME = "JPUSH_EXAMPLE";
    public static final String PREFS_DAYS = "JPUSH_EXAMPLE_DAYS";
    public static final String PREFS_START_TIME = "PREFS_START_TIME";
    public static final String PREFS_END_TIME = "PREFS_END_TIME";
    private static final String JPUSH_APP_KEY = "JPUSH_APPKEY";

    /**
     * 方法描述: 获取在清单文件中填入的极光推送服务的AppKey
     */
    public static String getJPUSHAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != applicationInfo)
                metaData = applicationInfo.metaData;
            if (null != metaData) {
                appKey = metaData.getString(JPUSH_APP_KEY);
                if ((null == appKey) || appKey.length() != 24) {
                    appKey = null;
                }
            }
        } catch (NameNotFoundException e) {
            Log.e("JPUSHTools---GetAppKey", e.getMessage());
        }
        return appKey;
    }

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
     * 方法描述: 验证传入的参数是否是标签和别名    校验Tag(标签) Alias(别名) 只能是数字,英文字母和中文
     *
     * @param s 此处传入的是标签和别名
     */
    public static boolean isValidTagAndAlias(String s) {
        Pattern pattern = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * 方法描述: 验证多标签的参数是否有效,并返回多标签的Set集合
     */
    public static Set<String> isValidTags(String tags, String partitionTagSymbol) {
        tags = tags.trim();// 去除空格
        if (TextUtils.isEmpty(tags)) {// 空判断
            return null;
        }
        // partitionTagSymbol 隔开的多个 转换成 Set
        String[] sArray = tags.split(partitionTagSymbol);
        Set<String> tagSet = new LinkedHashSet<>();
        // 将标签条目存储进Set集合
        for (String tagItem : sArray) {
            if (!JPUSHTools.isValidTagAndAlias(tagItem)) {
                return null;
            }
            tagSet.add(tagItem);
        }
        // 集合空判断
        if (tagSet.isEmpty()) {
            return null;
        }
        // 集合所有子元素有效判断
        if (JPUSHTools.filterValidTags(tagSet).size() != tagSet.size()) {
            return null;
        }
        return tagSet;
    }


    /**
     * 方法描述: 通过极光推送的SDK，将参数中有效的标签筛选出来
     */
    public static Set<String> filterValidTags(Set<String> tags) {
        return JPushInterface.filterValidTags(tags);
    }

    /**
     * 方法描述: 在主线程中弹吐司
     */
    public static void showToast(final String toast, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    /**
     * 方法描述: 判断是否链接了网络
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    private static boolean isReadableASCII(CharSequence string) {
        if (TextUtils.isEmpty(string)) return false;
        try {
            Pattern p = Pattern.compile("[\\x20-\\x7E]+");
            return p.matcher(string).matches();
        } catch (Throwable e) {
            return true;
        }
    }
}
