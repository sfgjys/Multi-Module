package com.zhq.jpush;

import com.zhq.jpush.bean.AliasTagsBean;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;

/**
 * 方法描述: 将 观察者模式 传递过来的 标签别名操作结果 先进行分类处理
 */
public abstract class DistributeHandleResult implements SingleObserver<Object> {

    @Override
    public void onSuccess(@NonNull Object o) {
        AliasTagsBean aliasTagsBean = (AliasTagsBean) o;
        if (aliasTagsBean.isNeedAgainAction()) {
            suggestAgainAction(aliasTagsBean);
        } else {
            actionSuccess(aliasTagsBean);
        }
    }

    /**
     * 方法描述: 让使用者来决定具体的重试操作
     */
    public abstract void suggestAgainAction(AliasTagsBean aliasTagsBean);

    /**
     * 方法描述: 让使用者来决定具体的成功操作后该如何
     */
    public abstract void actionSuccess(AliasTagsBean aliasTagsBean);
}
