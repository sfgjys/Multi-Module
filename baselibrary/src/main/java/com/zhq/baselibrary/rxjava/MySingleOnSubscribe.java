package com.zhq.baselibrary.rxjava;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * 使用Single的create方法时，需要传递的对象参数
 */
public class MySingleOnSubscribe implements SingleOnSubscribe<Object> {

    private Object mData;

    public void updateData(Object mData) {
        this.mData = mData;
    }

    @Override
    public void subscribe(@NonNull SingleEmitter<Object> e) throws Exception {
        if (mData != null) {
            e.onSuccess(mData);
        } else {
            e.onError(new NullPointerException());
        }
    }
}

