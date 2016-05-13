package com.viclee.beziercurve;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * 自定义贝塞尔曲线估值器
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {
    PointF mPointF1;
    PointF mPointF2;

    public BezierEvaluator(PointF mPointF1, PointF mPointF2) {
        this.mPointF1 = mPointF1;
        this.mPointF2 = mPointF2;
    }

    @Override
    public PointF evaluate(float t, PointF point0, PointF point3) {
        //t 百分比 0~1
        PointF pointF = new PointF();
        //套用公式进行计算,三次方贝塞尔曲线
        //曲线起始于P0走向P1，并从P2的方向来到P3。一般不会经过P1或P2;这两个点只是在那里提供方向资讯。
        //P0和P1之间的间距，决定了曲线在转而趋进P3之前，走向P2方向的“长度有多长”。
        //http://blog.csdn.net/androidzhaoxiaogang/article/details/8680330
        //http://www.cnblogs.com/benhero/p/4377374.html?utm_source=tuicool&utm_medium=referral
        pointF.x = point0.x * (1 - t) * (1 - t) * (1 - t)
                + 3 * mPointF1.x * t * (1 - t) * (1 - t)
                + 3 * mPointF2.x * t * t * (1 - t)
                + point3.x * t * t * t;

        pointF.y = point0.y * (1 - t) * (1 - t) * (1 - t)
                + 3 * mPointF1.y * t * (1 - t) * (1 - t)
                + 3 * mPointF2.y * t * t * (1 - t)
                + point3.y * t * t * t;
        return pointF;
    }
}
