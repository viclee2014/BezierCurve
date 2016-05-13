package com.viclee.beziercurve;

import android.view.animation.Interpolator;

/**
 * Created by lixueyong on 16/5/12.
 */
public class BezierInterpolator implements Interpolator {
    float p1;
    float p2;
    BezierInterpolator(float p1, float p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    @Override
    public float getInterpolation(float t) {
        //曲线的生成：http://cubic-bezier.com/
        return 0 * (1 - t) * (1 - t) * (1 - t)
                + 3 * p1 * t * (1 - t) * (1 - t)
                + 3 * p2 * t * t * (1 - t)
                + 1 * t * t * t;
    }
}
