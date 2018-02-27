package com.zhq.custom_view.banner;

import android.annotation.SuppressLint;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 类的描述: 轮播图的容器
 */
public class PagerContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    /**
     * 变量的描述: 本控件下的第一个子控件必须是ViewPage，该ViewPage就是轮播图本身
     */
    private ViewPager mViewPager;
    /**
     * 变量的描述: Page条目的点击事件监听
     */
    private PageItemClickListener mPageItemClickListener;
    /**
     * 变量的描述: 是否需要重画
     */
    private boolean mNeedsRedraw;

    public PagerContainer(@NonNull Context context) {
        super(context);
        init();
    }

    public PagerContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagerContainer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 是否限制子View在其范围内，我们将其值设置为false后那么当子控件的高度高于父控件时也会完全显示,而不会被压缩
        setClipChildren(false);

        // 如果取消了子控件在父控件中的高度限制，那么在Android 3.x/4.x上不能使用硬件加速
        // 而想要进行硬件加速，则需要在这里进行代码设置
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * 方法描述: 设置Page条目的点击事件
     */
    public void setPageItemClickListener(PageItemClickListener pageItemClickListener) {
        this.mPageItemClickListener = pageItemClickListener;
    }

    /**
     * 方法描述: 获取容器中的ViewPager
     */
    public ViewPager getViewPager() {
        return mViewPager;
    }

    // **************************************************** 重写父类的方法 ****************************************************
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onFinishInflate() { // 这个方法代表自定义控件中的子控件映射完成了，然后可以获取子控件进行一些初始化控件的操作
        try {
            mViewPager = (ViewPager) getChildAt(0);// 获取本控件下序列的第一个子控件
            mViewPager.addOnPageChangeListener(this);// 设置Page改变监听
        } catch (Exception e) {
            throw new IllegalStateException("The root child of PagerContainer must be a ViewPager");
        }
    }

    /**
     * 变量的描述: 中心Point
     */
    private Point mCenter = new Point();
    /**
     * 变量的描述: 触摸时的初始Point
     */
    private Point mInitialTouch = new Point();

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        mCenter.x = width / 2;
        mCenter.y = height / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialTouch.x = (int) event.getX();
                mInitialTouch.y = (int) event.getY();
                event.offsetLocation(mCenter.x - mInitialTouch.x, mCenter.y - mInitialTouch.y);
                break;
            case MotionEvent.ACTION_UP:
                int delta = Utils.isInNonTappableRegion(getWidth(), mViewPager.getWidth(), mInitialTouch.x, event.getX());
                if (delta != 0) {
                    int preItem = mViewPager.getCurrentItem();
                    int currentItem = preItem + delta;
                    mViewPager.setCurrentItem(currentItem);
                    event.offsetLocation(mCenter.x - mInitialTouch.x, mCenter.y - mInitialTouch.y);
                    if (mPageItemClickListener != null) {
                        mPageItemClickListener.onItemClick(mViewPager.getChildAt(currentItem), currentItem);
                    }
                }
                break;
        }
        return mViewPager.dispatchTouchEvent(event);
    }

    // **************************************************** 图片轮播改变的监听 ****************************************************
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // Page滑动监听
        if (mNeedsRedraw) {
            invalidate();
        }
    }

    @Override
    public void onPageSelected(int position) {
        // Page被选择后的监听
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // Page滑动状态改变监听
        mNeedsRedraw = (state != ViewPager.SCROLL_STATE_IDLE);
    }
}
