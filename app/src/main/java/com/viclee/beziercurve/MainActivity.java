package com.viclee.beziercurve;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageView mImageView;
    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_draw);

        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels - 200;

        mImageView = (ImageView) findViewById(R.id.image_bezier);
        mImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_bezier) {
            ValueAnimator bezierValueAnimator = getBezierValueAnimator();
            bezierValueAnimator.start();
        }
    }

    private ValueAnimator getBezierValueAnimator() {
        PointF pointf0 = new PointF(0, 0);
        PointF pointf1 = new PointF(width / 8, height * 7 / 8);
        PointF pointf2 = new PointF(width * 7 / 8, height / 8);
        PointF pointf3 = new PointF(width - 100, height - 100);

        //通过贝塞尔曲线公式，自定义估值器
        final BezierEvaluator evaluator = new BezierEvaluator(pointf1, pointf2);
        //将估值器传入属性动画，不断的修改控件的坐标
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointf0, pointf3);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointf = (PointF) animation.getAnimatedValue();
                mImageView.setX(pointf.x);
                mImageView.setY(pointf.y);
            }
        });
        animator.setTarget(mImageView);
        animator.setDuration(3000);
        //同样，为了美观我们还可以添加加速度,减速度，弹射等效果(插值器)
        animator.setInterpolator(new BezierInterpolator(1.61f, -0.26f));

        return animator;
    }
}
