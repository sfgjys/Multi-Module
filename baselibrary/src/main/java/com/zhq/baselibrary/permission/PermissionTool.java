package com.zhq.baselibrary.permission;

import android.Manifest;

/**
 * 类的描述: 权限工具
 */
public class PermissionTool {

    public static String getDangerPermissionGroupName(String permissionName) {
        if (Manifest.permission.READ_CALENDAR.equals(permissionName)
                || Manifest.permission.WRITE_CALENDAR.equals(permissionName)) {
            return "日历相关的运行权限";
        } else if (Manifest.permission.CAMERA.equals(permissionName)) {
            return "访问照相机或从设备捕获图像/视频的权限";
        } else if (Manifest.permission.READ_CONTACTS.equals(permissionName)
                || Manifest.permission.WRITE_CONTACTS.equals(permissionName)
                || Manifest.permission.GET_ACCOUNTS.equals(permissionName)) {
            return "获取或者读写手机联系人的权限";
        } else if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permissionName)
                || Manifest.permission.ACCESS_COARSE_LOCATION.equals(permissionName)) {
            return "访问手机位置的权限。";
        } else if (Manifest.permission.RECORD_AUDIO.equals(permissionName)) {
            return "捕获手机音频的权限";
        } else if (Manifest.permission.READ_PHONE_STATE.equals(permissionName)
                || Manifest.permission.CALL_PHONE.equals(permissionName)
                || Manifest.permission.READ_CALL_LOG.equals(permissionName)
                || Manifest.permission.WRITE_CALL_LOG.equals(permissionName)
                || Manifest.permission.ADD_VOICEMAIL.equals(permissionName)
                || Manifest.permission.USE_SIP.equals(permissionName)
                || Manifest.permission.PROCESS_OUTGOING_CALLS.equals(permissionName)) {
            return "获取手机状态或者通话记录的权限";
        } else if (Manifest.permission.BODY_SENSORS.equals(permissionName)) {
            return "访问手机传感器的权限";
        } else if (Manifest.permission.SEND_SMS.equals(permissionName)
                || Manifest.permission.RECEIVE_SMS.equals(permissionName)
                || Manifest.permission.READ_SMS.equals(permissionName)
                || Manifest.permission.RECEIVE_WAP_PUSH.equals(permissionName)
                || Manifest.permission.RECEIVE_MMS.equals(permissionName)) {
            return "获取或者读写手机短信的权限";
        } else if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permissionName)
                || Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissionName)) {
            return "读写外部存储器的权限";
        } else {
            return "该权限没有对应的危险权限组";
        }
    }
}
