package com.zhq.jpush;

import android.content.Context;
import android.os.Message;
import android.util.SparseArray;

import com.zhq.jpush.bean.AliasTagsBean;
import com.zhq.jpush.tool.JPUSHLogTools;
import com.zhq.jpush.tool.JPUSHTools;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;

/**
 * 类的描述: 别名和标签的操作帮助类
 */
public class AliasTagOperatorHelper {
    private static final String TAG = "AliasTagOperatorHelper";
    /**
     * 变量描述: 别名和标签的操作序列号
     */
    public static int sequence = 1;
    // 获取上下文
    private Context context;

    public void init(Context context) {
        if (context != null) {
            this.context = context.getApplicationContext();
        }
    }
    // 获取上下文

    // 单例模式
    private static AliasTagOperatorHelper mInstance;

    private AliasTagOperatorHelper() {
    }

    public static AliasTagOperatorHelper getInstance() {
        if (mInstance == null) {
            synchronized (AliasTagOperatorHelper.class) {
                if (mInstance == null) {
                    mInstance = new AliasTagOperatorHelper();
                }
            }
        }
        return mInstance;
    }
    // 单例模式

    // 操作别名标签动作的缓存
    private SparseArray<AliasTagsBean> mOperatorAliasTagActionCacheArray = new SparseArray<>();

    public AliasTagsBean getActionCache(int sequence) {
        return mOperatorAliasTagActionCacheArray.get(sequence);
    }

    public void removeActionCache(int sequence) {
        mOperatorAliasTagActionCacheArray.remove(sequence);
    }

    public void putActionCache(int sequence, AliasTagsBean tagAliasBean) {
        mOperatorAliasTagActionCacheArray.put(sequence, tagAliasBean);
    }
    // 操作别名标签动作的缓存


    /**
     * 方法描述: 处理标签别名动作
     *
     * @param sequence 使用本类的全局静态变量sequence++
     * @param context  最好使用getApplicationContext
     */
    public void handleAction(Context context, int sequence, AliasTagsBean aliasTagsBean) {
        init(context);
        if (aliasTagsBean == null) {
            JPUSHLogTools.w(TAG, "操作别名或者标签时需要的参数Bean类对象为空");
            return;
        }
        // 存储缓存
        putActionCache(sequence, aliasTagsBean);

        if (aliasTagsBean.isAliasAction()) {// 是操作别名的动作
            switch (aliasTagsBean.getWhichAction()) {
                case AliasTagsBean.ALIAS_ACTION_SET:
                    // 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符@!#$&*+=.|。
                    // 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
                    JPushInterface.setAlias(context, sequence, aliasTagsBean.getAliasData());// 会覆盖之前的设置。
                    break;
                case AliasTagsBean.ALIAS_ACTION_GET:
                    JPushInterface.getAlias(context, sequence);// 查询别名
                    break;
                case AliasTagsBean.ALIAS_ACTION_DELETE:
                    JPushInterface.deleteAlias(context, sequence);// 删除别名
                    break;
                default:
                    JPUSHLogTools.w(TAG, "不支持的别名操作类型");
                    break;
            }
        } else {// 是操作标签的动作
            switch (aliasTagsBean.getWhichAction()) {
                case AliasTagsBean.TAG_ACTION_SET:
                    // 有效的标签组成：字母（区分大小写）、数字、下划线、汉字、特殊字符@!#$&*+=.|。
                    // 限制：每个 tag 命名长度限制为 40 字节，最多支持设置 1000 个 tag，且单次操作总长度不得超过5000字节。（判断长度需采用UTF-8编码）
                    // 单个设备最多支持设置 1000 个 tag。App 全局 tag 数量无限制。
                    JPushInterface.setTags(context, sequence, aliasTagsBean.getTagsData());// 设置Tag，会覆盖之前的设置。
                    break;
                case AliasTagsBean.TAG_ACTION_ADD:
                    JPushInterface.addTags(context, sequence, aliasTagsBean.getTagsData());// 每次调用至少新增一个 tag。
                    break;
                case AliasTagsBean.TAG_ACTION_DELETE:
                    JPushInterface.deleteTags(context, sequence, aliasTagsBean.getTagsData());// 每次调用至少删除一个 tag。
                    break;
                case AliasTagsBean.TAG_ACTION_CLEAN_ALL:
                    JPushInterface.cleanTags(context, sequence);// 清除所有标签。
                    break;
                case AliasTagsBean.TAG_ACTION_GET_ALL:
                    JPushInterface.getAllTags(context, sequence);// 查询所有标签。
                    break;
                case AliasTagsBean.TAG_ACTION_CHECK:
                    // 一次只能check一个tag
                    String tag = (String) aliasTagsBean.getTagsData().toArray()[0];
                    JPushInterface.checkTagBindState(context, sequence, tag);// 查询指定tag与当前用户绑定的状态。
                    break;
                default:
                    JPUSHLogTools.w(TAG, "不支持的标签操作类型");
                    break;
            }
        }
    }

    /**
     * 方法描述: tag增删查改的操作会在此方法中回调结果。
     */
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        int errorCode = jPushMessage.getErrorCode();// 0为成功，其他返回码请参考错误码定义.
        boolean tagCheckStateResult = jPushMessage.getTagCheckStateResult();// 开发者想要查询的tag与当前用户绑定的状态。
        String checkTag = jPushMessage.getCheckTag();// 开发者想要查询绑定状态的tag。
        Set<String> tags = jPushMessage.getTags();// 开发者传或查询得到的tags。

        JPUSHLogTools.i(TAG, "标签的增删查改的操作结果, sequence:" + sequence + ",tags:" + tags + ",tags集合大小:" + tags.size());
        init(context);

        //根据sequence从之前操作缓存中获取缓存记录
        AliasTagsBean aliasTagsBean = mOperatorAliasTagActionCacheArray.get(sequence);
        if (aliasTagsBean == null) {
            JPUSHTools.showToast("获取缓存记录失败", context);
            return;
        }

        if (errorCode == 0) {
            mOperatorAliasTagActionCacheArray.remove(sequence);
            String logs = getActionString(aliasTagsBean.getWhichAction()) + "成功了";
            JPUSHLogTools.i(TAG, logs);
        } else {
            String logs = getActionString(aliasTagsBean.getWhichAction()) + "失败了";
            if (errorCode == 6018) {
                //tag数量超过限制,需要先清除一部分再add
                logs += "，Tag数量超过限制,需要先清除一部分";
            }
            logs += ", errorCode:" + jPushMessage.getErrorCode();
            JPUSHLogTools.e(TAG, logs);

            // 操作失败，尝试重新操作
            if (!RetryActionIfNeeded(errorCode, aliasTagsBean)) {
                JPUSHTools.showToast(logs, context);
            }
        }
    }

    /**
     * 方法描述: 查询某个tag与当前用户的绑定状态的操作会在此方法中回调结果。
     */
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        int errorCode = jPushMessage.getErrorCode();// 0为成功，其他返回码请参考错误码定义.
        Set<String> tags = jPushMessage.getTags();// 开发者传或查询得到的tags。
        boolean tagCheckStateResult = jPushMessage.getTagCheckStateResult();// 开发者想要查询的tag与当前用户绑定的状态。
        String checkTag = jPushMessage.getCheckTag();// 开发者想要查询绑定状态的tag。

        JPUSHLogTools.i(TAG, "查询指定标签与当前用户的绑定状态的结果, sequence:" + sequence + ",checkTag:" + checkTag + ",tagCheckStateResult:" + tagCheckStateResult);
        init(context);

        //根据sequence从之前操作缓存中获取缓存记录
        AliasTagsBean aliasTagsBean = mOperatorAliasTagActionCacheArray.get(sequence);
        if (aliasTagsBean == null) {
            JPUSHTools.showToast("获取缓存记录失败", context);
            return;
        }

        if (errorCode == 0) {
            mOperatorAliasTagActionCacheArray.remove(sequence);
            String logs = getActionString(aliasTagsBean.getWhichAction()) + "-成功了，" + checkTag + "标签与当前用户的绑定状态是:" + tagCheckStateResult;
            JPUSHLogTools.i(TAG, logs);
        } else {
            String logs = getActionString(aliasTagsBean.getWhichAction()) + "-失败了, errorCode:" + jPushMessage.getErrorCode();
            JPUSHLogTools.e(TAG, logs);

            // 操作失败，尝试重新操作
            if (!RetryActionIfNeeded(errorCode, aliasTagsBean)) {
                JPUSHTools.showToast(logs, context);
            }
        }
    }

    /**
     * 方法描述: alias相关的操作会在此方法中回调结果。
     */
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        int errorCode = jPushMessage.getErrorCode();// 0为成功，其他返回码请参考错误码定义.
        String alias = jPushMessage.getAlias();// 开发者传或查询得到的alias。

        JPUSHLogTools.i(TAG, "别名操作后的结果, sequence:" + sequence + ",alias:" + alias + ",errorCode:" + errorCode);
        init(context);

        //根据sequence从之前操作缓存中获取缓存记录
        AliasTagsBean aliasTagsBean = mOperatorAliasTagActionCacheArray.get(sequence);
        if (aliasTagsBean == null) {
            JPUSHTools.showToast("获取缓存记录失败", context);
            return;
        }

        if (errorCode == 0) {// 别名操作成功
            mOperatorAliasTagActionCacheArray.remove(sequence);
            String logs = getActionString(aliasTagsBean.getWhichAction()) + "-成功了";
            JPUSHLogTools.i(TAG, logs);
        } else {// 别名操作失败
            String logs = getActionString(aliasTagsBean.getWhichAction()) + "-失败了, errorCode:" + jPushMessage.getErrorCode();
            JPUSHLogTools.e(TAG, logs);

            // 操作失败，尝试重新操作
            if (!RetryActionIfNeeded(errorCode, aliasTagsBean)) {
                JPUSHTools.showToast(logs, context);
            }
        }
    }

    private String getActionString(int actionType) {
        switch (actionType) {
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

    private boolean RetryActionIfNeeded(int errorCode, AliasTagsBean aliasTagsBean) {
        if (!JPUSHTools.isConnected(context)) {
            JPUSHLogTools.w(TAG, "手机网络异常");
            return false;
        }
        //返回的错误码为6002 超时,6014 服务器繁忙,都建议延迟重试
        if (errorCode == 6002 || errorCode == 6014) {
            JPUSHLogTools.d(TAG, "可以尝试进行操作重试");
            if (aliasTagsBean != null) {
                Message message = new Message();
//                message.what = DELAY_SEND_ACTION;
//                message.obj = aliasTagsBean;
//                delaySendHandler.sendMessageDelayed(message, 1000 * 60);
//                String logs = getRetryStr(aliasTagsBean.isAliasAction, aliasTagsBean.action, errorCode);
//                ExampleUtil.showToast(logs, context);
                return true;
            }
        }
        return false;
    }
}
