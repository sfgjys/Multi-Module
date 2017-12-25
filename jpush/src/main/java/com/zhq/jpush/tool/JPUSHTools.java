package com.zhq.jpush.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JPUSHTools {
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
        if (JPushSdkTools.filterValidTags(tagSet).size() != tagSet.size()) {
            return null;
        }
        return tagSet;
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
