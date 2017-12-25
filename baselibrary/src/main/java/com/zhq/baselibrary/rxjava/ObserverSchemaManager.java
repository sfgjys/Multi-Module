package com.zhq.baselibrary.rxjava;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

/**
 * 第一组：ObservableSource/Observer
 * 一次可发送单条数据或者数据序列onNext，可发送完成通知onComplete或异常通知onError，不支持背压。
 * 第二组：Publisher/Subscriber
 * 第一组基础上进行改进，支持背压，一次可发送单条数据或者数据序列onNext，可发送完成通知onComplete或异常通知onError，但效率没有第一组高。
 * 第三组：SingleSource/SingleObserver
 * 第一组简化版，只能发送单条数据onSuccess，或者异常通知onError
 * 第四组：CompletableSource/CompletableObserve
 * 第一组简化版，不能发送数据，只发送完成通知onComplete或者异常通知onError
 * 第五组：MaybeSource/MaybeObserver
 * 第三，第四组的合并版，只能发送单条数据onSuccess和完成通知onComplete或者发送一条异常通知onError
 */
public class ObserverSchemaManager {

    // 获取本类的单例对象
    private static ObserverSchemaManager mInstance;

    private ObserverSchemaManager() {
    }

    public static ObserverSchemaManager getInstance() {
        if (mInstance == null) {
            synchronized (ObserverSchemaManager.class) {
                if (mInstance == null) {
                    mInstance = new ObserverSchemaManager();
                }
            }
        }
        return mInstance;
    }
    // 获取本类的单例对象

    private HashMap<String, Single<Object>> mMultiObservable = new HashMap<>();
    private HashMap<String, MySingleOnSubscribe> mMultiOnSubscribe = new HashMap<>();
    private HashMap<String, SingleObserver<Object>> mMultiObserver = new HashMap<>();

    /**
     * 创建Single和其内部对象SingleOnSubscribe，或者更新SingleOnSubscribe发送消息时使用的数据对象
     */
    public void createObservable(String tag, Object data) {
        if (mMultiOnSubscribe.get(tag) == null) {
            MySingleOnSubscribe mySingleOnSubscribe = new MySingleOnSubscribe();
            mySingleOnSubscribe.updateData(data);
            mMultiOnSubscribe.put(tag, mySingleOnSubscribe);
        } else {
            mMultiOnSubscribe.get(tag).updateData(data);
        }

        if (mMultiObservable.get(tag) == null) {
            Single<Object> single = Single.create(mMultiOnSubscribe.get(tag));
            mMultiObservable.put(tag, single);
        }
    }

    /**
     * 将SingleObserver进行存储，让Single可以调用subscribe方法进行订阅关系建立
     */
    public void saveObserver(String tag, SingleObserver<Object> singleObserver) {
        if (mMultiObserver.get(tag) == null) {
            mMultiObserver.put(tag, singleObserver);
        }
    }

    /**
     * 通用的建立订阅关系
     */
    public void subscribe(String tag) {
        if (mMultiObserver.get(tag) == null) {
            mMultiObservable.get(tag).subscribe();
        } else {
            mMultiObservable.get(tag).subscribe(mMultiObserver.get(tag));
        }
    }

    /**
     * 移除指定的被观察者
     */
    public void removeAppointObservable(String tag) {
        mMultiObservable.remove(tag);
        mMultiOnSubscribe.remove(tag);
    }

    /**
     * 移除指定的观察者
     */
    public void removeAppointObserver(String tag) {
        mMultiObserver.remove(tag);
    }
}
