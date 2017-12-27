package com.zhq.jpush.aliastag;

import android.content.Context;
import android.util.SparseArray;

import com.zhq.baselibrary.rxjava.ObserveSerialNumber;
import com.zhq.baselibrary.rxjava.ObserverSchemaManager;
import com.zhq.jpush.bean.AliasTagsBean;
import com.zhq.jpush.tool.JPUSHLogTools;
import com.zhq.jpush.tool.JPUSHTools;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import io.reactivex.SingleObserver;

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

    private void init(Context context) {
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

    // 操作别名标签动作的缓存集合
    private SparseArray<AliasTagsBean> mOperatorAliasTagActionCacheArray = new SparseArray<>();


    /**
     * 方法描述: 处理标签别名动作
     *
     * @param sequence 使用本类的全局静态变量sequence++
     * @param context  最好使用getApplicationContext
     */
    public void handleAction(Context context, int sequence, AliasTagsBean aliasTagsBean, SingleObserver<Object> singleObserver) {
        init(context);
        if (aliasTagsBean == null) {
            JPUSHLogTools.w(TAG, "操作别名或者标签时需要的参数Bean类对象为空");
            return;
        }
        // 存储缓存
        mOperatorAliasTagActionCacheArray.put(sequence, aliasTagsBean);

        // 设置操作结果的回调
        ObserverSchemaManager.getInstance().setObserveListenCallback(ObserveSerialNumber.OBSERVE_ALIAS_TAG_OPERATOR_RESULT, singleObserver);

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
    void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        int errorCode = jPushMessage.getErrorCode();// 0为成功，其他返回码请参考错误码定义.
        Set<String> tags = jPushMessage.getTags();// 开发者传或查询得到的tags。

        JPUSHLogTools.i(TAG, "标签的增删查改的操作结果, sequence:" + sequence + ",tags:" + tags + ",tags集合大小:" + tags.size());
        init(context);

        //根据sequence从之前操作缓存中获取缓存记录
        AliasTagsBean aliasTagsBean = mOperatorAliasTagActionCacheArray.get(sequence);
        if (aliasTagsBean == null) {
            JPUSHTools.showToast("获取缓存记录失败", context);
            return;
        }

        aliasTagsBean.setActionResult(jPushMessage);

        // 不管操作是否成功，都需要将缓存移除
        mOperatorAliasTagActionCacheArray.remove(sequence);

        if (errorCode == 0) {// 成功后从mOperatorAliasTagActionCacheArray集合中移除信息，然后将操作结果传递出去
            String logs = aliasTagsBean.getActionName() + "成功了";
            JPUSHLogTools.i(TAG, logs);

            transmitOperatorResult(ObserveSerialNumber.OBSERVE_ALIAS_TAG_OPERATOR_RESULT, aliasTagsBean);
        } else {
            String logs = aliasTagsBean.getActionName() + "失败了";
            if (errorCode == 6018) {
                //tag数量超过限制,需要先清除一部分再add
                logs += "，Tag数量超过限制,需要先清除一部分";
            }
            logs += ", errorCode:" + jPushMessage.getErrorCode();
            JPUSHLogTools.e(TAG, logs);

            handleFailureResults(aliasTagsBean, logs);
        }
    }

    /**
     * 方法描述: 查询某个tag与当前用户的绑定状态的操作会在此方法中回调结果。
     */
    void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        int errorCode = jPushMessage.getErrorCode();// 0为成功，其他返回码请参考错误码定义.
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

        aliasTagsBean.setActionResult(jPushMessage);

        // 不管操作是否成功，都需要将缓存移除
        mOperatorAliasTagActionCacheArray.remove(sequence);

        if (errorCode == 0) {// 成功后从mOperatorAliasTagActionCacheArray集合中移除信息，然后将操作结果传递出去
            String logs = aliasTagsBean.getActionName() + "-成功了，" + checkTag + "标签与当前用户的绑定状态是:" + tagCheckStateResult;
            JPUSHLogTools.i(TAG, logs);

            transmitOperatorResult(ObserveSerialNumber.OBSERVE_ALIAS_TAG_OPERATOR_RESULT, aliasTagsBean);
        } else {
            String logs = aliasTagsBean.getActionName() + "-失败了, errorCode:" + jPushMessage.getErrorCode();
            JPUSHLogTools.e(TAG, logs);

            handleFailureResults(aliasTagsBean, logs);
        }
    }

    /**
     * 方法描述: alias相关的操作会在此方法中回调结果。
     */
    void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
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

        aliasTagsBean.setActionResult(jPushMessage);

        // 不管操作是否成功，都需要将缓存移除
        mOperatorAliasTagActionCacheArray.remove(sequence);

        if (errorCode == 0) {// 成功后从mOperatorAliasTagActionCacheArray集合中移除信息，然后将操作结果传递出去
            String logs = aliasTagsBean.getActionName() + "-成功了";
            JPUSHLogTools.i(TAG, logs);

            transmitOperatorResult(ObserveSerialNumber.OBSERVE_ALIAS_TAG_OPERATOR_RESULT, aliasTagsBean);
        } else {// 别名操作失败
            String logs = aliasTagsBean.getActionName() + "-失败了, errorCode:" + jPushMessage.getErrorCode();
            JPUSHLogTools.e(TAG, logs);

            handleFailureResults(aliasTagsBean, logs);
        }
    }

    /**
     * 方法描述: 操作失败，尝试重新操作
     */
    private void handleFailureResults(AliasTagsBean aliasTagsBean, String logs) {
        if (RetryActionIfNeeded(aliasTagsBean.getActionResult().getErrorCode())) {
            aliasTagsBean.setNeedAgainAction(true);
            transmitOperatorResult(ObserveSerialNumber.OBSERVE_ALIAS_TAG_OPERATOR_RESULT, aliasTagsBean);
        } else {
            JPUSHTools.showToast(logs, context);
        }
    }

    /**
     * 方法描述: 本方法中先判断手机网络是否异常，在判断操作失败的错误码是否是6002或者6014
     * 如果网络异常，或者错误码不是6002或者6014，那么就返回false，弹出错误提示土司。否者就尝试重新开始操作，并返回true
     */
    private boolean RetryActionIfNeeded(int errorCode) {
        if (!JPUSHTools.isConnected(context)) {
            JPUSHLogTools.w(TAG, "手机网络异常");
            return false;
        }
        // 返回的错误码为6002 超时,6014 服务器繁忙,都建议延迟重试
        if (errorCode == 6002 || errorCode == 6014) {
            JPUSHLogTools.d(TAG, "可以尝试进行操作重试");
            return true;
        }
        return false;
    }

    /**
     * 方法描述: 通过观察者模式将操作的结果传递出去
     */
    private void transmitOperatorResult(int tag, AliasTagsBean aliasTagsBean) {
        ObserverSchemaManager.getInstance().createObservable(tag, aliasTagsBean);
        ObserverSchemaManager.getInstance().subscribe(tag);
    }
}
