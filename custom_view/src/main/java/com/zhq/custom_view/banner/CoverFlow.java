package com.zhq.custom_view.banner;

import android.support.v4.view.ViewPager;

/**
 * Created by ZHQ on 2018/2/27.
 */

public class CoverFlow {
    private final ViewPager viewPager;
    private final float scaleValue;
    private final float pagerMargin;
    private final float spaceSize;
    private final float rotationY = 0;

    public CoverFlow(CoverFlow.Builder builder) {
        if (null == builder) {
            throw new IllegalArgumentException("A non-null CoverFlow.Builde must be provided");
        }

        this.viewPager = builder.viewPager;
        this.scaleValue = builder.scaleValue;
        this.pagerMargin = builder.pagerMargin;
        this.spaceSize = builder.spaceSize;

        if (this.viewPager != null) {
            this.viewPager.setPageTransformer(false, new CoverTransformer(this.scaleValue, this.pagerMargin, this.spaceSize, this.rotationY));
        }
    }

    public static class Builder {
        private ViewPager viewPager;
        private float scaleValue;
        private float pagerMargin;
        private float spaceSize;

        public CoverFlow.Builder with(ViewPager viewPager) {
            this.viewPager = viewPager;
            return this;
        }

        public CoverFlow.Builder scale(float scaleValue) {
            this.scaleValue = scaleValue;
            return this;
        }

        public CoverFlow.Builder pagerMargin(float pagerMargin) {
            this.pagerMargin = pagerMargin;
            return this;
        }

        public CoverFlow.Builder spaceSize(float spaceSize) {
            this.spaceSize = spaceSize;
            return this;
        }

        public CoverFlow build() {
            return new CoverFlow(this);
        }
    }
}
