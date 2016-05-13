package com.viclee.beziercurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lixueyong on 16/5/11.
 */
public class BezierDrawView extends View {
    private float mX;
    private float mY;

    private final Paint mGesturePaint = new Paint();
    private final Path mPath = new Path();

    public BezierDrawView(Context context) {
        this(context, null);
    }

    public BezierDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(8);
        mGesturePaint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                handleMove(event);
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mGesturePaint);
    }

    private void handleDown(MotionEvent event) {
        mPath.reset();
        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;
        mPath.moveTo(x, y);
    }

    private void handleMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float preX = mX;
        final float preY = mY;

        final float dx = Math.abs(x - preX);
        final float dy = Math.abs(y - preY);

        //两点之间的距离大于等于3时，生成贝塞尔绘制曲线
        if (dx >= 3 || dy >= 3) {
            //设置贝塞尔曲线的操作点为起点和终点的一半
            float cX = (x + preX) / 2;
            float cY = (y + preY) / 2;

            //二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
            mPath.quadTo(preX, preY, cX, cY);
//            mPath.lineTo(x, y);

            mX = x;
            mY = y;
        }
    }
}
