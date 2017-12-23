package com.zhq.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zhq.jpush.tool.JPUSHLogTools;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();

            // 注意：  ~~~~~~~~~~~~~~~~~~~~~~ 下面几个Action要想能用必须在清单文件中先进行配置 ~~~~~~~~~~~~~~~~~~~~~~

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {// --------------------接收Registration ID
                // 极光推送SDK 向 极光推送服务器 注册所得到的 注册 全局唯一的 ID ，该ID通过本广播接收
                // 该全局唯一的 ID 是在安装App时根据手机来生成的，所以如果换了一部手机那么 ID 就不同了
                // 当使用 ID 来绑定用户的时候，需要每次获取下 ID 来对比一开始获取并存储的 ID 是否是同一个
                String registrationID = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                JPUSHLogTools.e(TAG, "JPush广播接收的 Registration ID : " + registrationID);
                // TODO 官方推荐 使用极光推送提供的别名与标签功能来绑定App与用户 https://docs.jiguang.cn/jpush/client/Android/android_senior/#_1
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {// --------------------接收自定义消息
                String messageTitle = bundle.getString(JPushInterface.EXTRA_TITLE);// 服务器推送下来的自定义消息的标题
                String messageContent = bundle.getString(JPushInterface.EXTRA_MESSAGE);// 服务器推送下来的自定义消息内容
                String messageAdditionalFields = bundle.getString(JPushInterface.EXTRA_EXTRA);// 服务器推送下来的附加字段。这是个 JSON 字符串。
                String messageID = bundle.getString(JPushInterface.EXTRA_MSG_ID);// 唯一标识消息的 ID, 可用于上报统计等。

                JPUSHLogTools.d(TAG, "JPush接收到推送下来的自定义消息 :" + " 标题 --- " + messageTitle + "； 内容 --- " + messageContent + "； 附加字段 --- " + messageAdditionalFields + "； 消息ID --- " + messageID);

                // TODO 将自定义消息的的内容发送出去，可以用自定义广播接收，也可以。。。。。。
                transmitCustomMessage(context, bundle);
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {// --------------------接收 通知(通知栏信息)
                // 如果通知的内容为空，则在通知栏上不会展示通知。
                // 但是，这个广播 Intent 还是会有。开发者可以取到通知内容外的其他信息。
                int notificationID = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);// 通知栏的Notification ID，可以用于清除Notification 如果服务端内容（alert）字段为空，则notification id 为0
                String notificationTitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);// 通知标题
                String notificationContent = bundle.getString(JPushInterface.EXTRA_ALERT);// 通知内容
                String notificationAdditionalFields = bundle.getString(JPushInterface.EXTRA_EXTRA);// 通知附加字段，这是个 JSON 字符串。
                String notificationPriority = bundle.getString(JPushInterface.EXTRA_NOTI_PRIORITY);// 通知的优先级 默认为0 传递过来的是Null，范围为 -2～2 ，其他值将会被忽略而采用默认。
                String messageID = bundle.getString(JPushInterface.EXTRA_MSG_ID);// 唯一标识通知消息的 ID, 可用于上报统计等。
                // 有的手机下面三种通知栏不能自动显示
                String bigTextContent = bundle.getString(JPushInterface.EXTRA_BIG_TEXT);// 大文本通知样式中大文本的内容。TODO 大文本通知是什么样的
                String bigPicturePath = bundle.getString(JPushInterface.EXTRA_BIG_PIC_PATH);// 大图片通知样式中大图片的路径/地址。 可支持本地图片的路径，或者填网络图片地址。TODO 大图片通知是什么样的
                String inboxJson = bundle.getString(JPushInterface.EXTRA_INBOX);// 收件箱通知样式中收件箱的内容。TODO 收件箱样式是什么样的

                JPUSHLogTools.d(TAG, notificationID + " - " + notificationTitle + " - " + notificationContent + " - " + notificationAdditionalFields + " - " + notificationPriority + " - " + messageID + " - " + bigTextContent + " - " + bigPicturePath + " - " + inboxJson);
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {// --------------------点击通知栏时极光推送SDK发送广播，本广播接收，在该action内自定义Intent，进行跳转
                // 因为富媒体有自己的点击通知栏功能，所以点击富媒体的通知栏，是不会接收到广播的
                int notificationID = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);// 通知栏的Notification ID，可以用于清除Notification
                String notificationTitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);// 通知标题
                String notificationContent = bundle.getString(JPushInterface.EXTRA_ALERT);// 通知内容
                String notificationAdditionalFields = bundle.getString(JPushInterface.EXTRA_EXTRA);// 通知附加字段，这是个 JSON 字符串。
                String messageID = bundle.getString(JPushInterface.EXTRA_MSG_ID);// 唯一标识调整消息的 ID, 可用于上报统计等。

                JPUSHLogTools.d(TAG, "JPush--用户点击了通知，开始自定义Intent，使用自定义的Intent开始跳转" + notificationID + " - " + notificationTitle + " - " + notificationContent + " - " + notificationAdditionalFields + " - " + messageID);

                // TODO 打开自定义的Intent
//				Intent i = new Intent(context, TestActivity.class);
//				i.putExtras(bundle);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );// 对activity任务栈进行设置
//				context.startActivity(i);
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {// --------------------接收富媒体通知
//                // TODO 下面两种富媒体的信息获取不到，并且不用处理，可直接显示富媒体UI
//                String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);// TODO 富媒体通知推送下载的HTML的文件路径,用于展现WebView。
//                String fileStr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);// TODO 富媒体通知推送下载的图片资源的文件名,多个文件名用 “，” 分开
//                String[] fileNames = new String[0];
//                if (fileStr != null)
//                    fileNames = fileStr.split(",");
//                fileNames.getClass();
                JPUSHLogTools.d(TAG, "接收富媒体通知: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {// --------------------接收JPush 服务的连接状态发生变化。（注：不是指 Android 系统的网络连接状态。）
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                JPUSHLogTools.w(TAG, "JPush 服务的连接状态是否发生变化: " + connected);
            } else {
                JPUSHLogTools.d(TAG, "JPush没有区分的Action是: " + intent.getAction());
            }
        } catch (Exception e) {
            JPUSHLogTools.d(TAG, "JPush接收通知时出现问题: " + e.getMessage());
        }
    }

    //send msg to MainActivity
    private void transmitCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!JPUSHTools.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//			}
//			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//		}
    }
}
