package com.zhq.umeng_share.tool;

import android.app.Activity;
import android.content.Context;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UmengTool;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhq.baselibrary.tool.GsonTools;
import com.zhq.umeng_share.ShareFieldsConfig;

import java.util.Map;

/**
 * 类的描述: 友盟社会化分享的工具
 */
public class ShareTools {

    /**
     * 方法描述: 在应用的Application中调用本方法初始化分享功能
     */
    public static void initializeShareFeature(Context context, boolean isOpenLog, boolean isOpenShareSDK) {
        // 设置友盟组件的LOG日志的开关，默认为false
        UMConfigure.setLogEnabled(isOpenLog);
        // 初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        // 参数二 友盟APP_KEY(这里为null，那必须在在清单文件中申明)  参数二 渠道名称(分享可以不用，如果要是用统计的话，这里为null，那必须在在清单文件中申明)
        // 参数三是类型  参数四是推送用的可以为""
        UMConfigure.init(context, null, null, UMConfigure.DEVICE_TYPE_PHONE, "");
        // 开启ShareSDK debug模式，方便定位错误，具体错误检查方式可以查看 http://dev.umeng.com/social/android/quick-integration 的报错必看，正式发布，请关闭该模式
        Config.DEBUG = isOpenShareSDK;
    }

    /**
     * 方法描述: 检查分享平台配置是否正确
     */
    public static void checkPlatformConfig(Context context, int whichPlatform) {
        switch (whichPlatform) {
            case ShareFieldsConfig.SIGN_PLATFORM:
                // 检查签名
                UmengTool.getSignature(context);
                break;
            case ShareFieldsConfig.SINA_PLATFORM:
                // 检查新浪
                UmengTool.checkSina(context);
                break;
            case ShareFieldsConfig.WEI_XIN_PLATFORM:
                // 检查微信
                UmengTool.checkWx(context);
                break;
            case ShareFieldsConfig.ALI_PAY_PLATFORM:
                // 检查支付宝
                UmengTool.checkAlipay(context);
                break;
            case ShareFieldsConfig.QQ_PLATFORM:
                // 检查QQ
                UmengTool.checkQQ(context);
                break;
            case ShareFieldsConfig.FACEBOOK_PLATFORM:
                // 检查Facebook
                UmengTool.checkFacebook(context);
                break;
            case ShareFieldsConfig.VK_PLATFORM:
                // 检查VK
                UmengTool.checkVK(context);
                break;
        }
    }

    /**
     * 方法描述: 将参数二转换为参数一Bean对象
     */
    public static Object mapDataSwitchBean(Class aClass, Map<String, String> map) {
        String jsonString = GsonTools.createGsonString(map);
        return GsonTools.changeGsonToBean(jsonString, aClass);
    }

    /**
     * 方法描述: 判断参数二平台是否授权过
     */
    public static boolean judgePlatformIsAuthorization(Activity activity, SHARE_MEDIA shareMedia) {
        return UMShareAPI.get(activity.getApplication()).isAuthorize(activity, shareMedia);
    }

    /**
     * 方法描述: 删除参数二平台的授权
     */
    public static void deletePlatformAuthorization(Activity activity, SHARE_MEDIA shareMedia, UMAuthListener umAuthListener) {
        if (judgePlatformIsAuthorization(activity, shareMedia)) {
            // 删除某个平台的授权
            UMShareAPI.get(activity.getApplication()).deleteOauth(activity, shareMedia, umAuthListener);
        } else {
            umAuthListener.onError(shareMedia, -1, new Throwable("该平台没有授权过"));
        }
    }
}
