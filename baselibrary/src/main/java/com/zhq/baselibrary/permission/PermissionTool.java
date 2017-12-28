package com.zhq.baselibrary.permission;

import android.Manifest;

/**
 * 类的描述: 权限工具
 */
public class PermissionTool {

    public static String getDangerPermissionGroupName(String permissionName) {
        if (Manifest.permission.READ_CALENDAR.equals(permissionName)
                || Manifest.permission.WRITE_CALENDAR.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.CAMERA.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.READ_CONTACTS.equals(permissionName)
                || Manifest.permission.WRITE_CONTACTS.equals(permissionName)
                || Manifest.permission.GET_ACCOUNTS.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permissionName)
                || Manifest.permission.ACCESS_COARSE_LOCATION.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.RECORD_AUDIO.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.READ_PHONE_STATE.equals(permissionName)
                || Manifest.permission.CALL_PHONE.equals(permissionName)
                || Manifest.permission.READ_CALL_LOG.equals(permissionName)
                || Manifest.permission.WRITE_CALL_LOG.equals(permissionName)
                || Manifest.permission.ADD_VOICEMAIL.equals(permissionName)
                || Manifest.permission.USE_SIP.equals(permissionName)
                || Manifest.permission.PROCESS_OUTGOING_CALLS.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.BODY_SENSORS.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.SEND_SMS.equals(permissionName)
                || Manifest.permission.RECEIVE_SMS.equals(permissionName)
                || Manifest.permission.READ_SMS.equals(permissionName)
                || Manifest.permission.RECEIVE_WAP_PUSH.equals(permissionName)
                || Manifest.permission.RECEIVE_MMS.equals(permissionName)) {
            return "";
        } else if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permissionName)
                || Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissionName)) {
            return "";
        } else {
            return "该权限没有对应的权限组";
        }
    }
}
