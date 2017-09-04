package com.hgz.test.customviewcirclerotate.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.hgz.test.customviewcirclerotate.R;

/**
 * Created by Administrator on 2017/9/4.
 */

public class MyCircle extends View {
    private float boundWidth;
    private int colors;
    private Paint paint;
    private int primevalColor;
    private float radius = 200;
    private int degrees = 0;
    private boolean isPause = false;
    private int speedDegrees=1;

    public MyCircle(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public MyCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCircle);
        colors = typedArray.getColor(R.styleable.MyCircle_circleBoundColor, 0Xffffff00);
        primevalColor = typedArray.getColor(R.styleable.MyCircle_circleBoundColor, 000000);
        boundWidth = typedArray.getDimension(R.styleable.MyCircle_circleBoundWidth, 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置圆的圆心的所在位置的xy坐标
        int pointX = getWidth() / 2;
        int pointY = getHeight() / 2;
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);

        paint.setStrokeWidth(boundWidth);
        paint.setColor(colors);
        //设置样式为空心
        paint.setStyle(Paint.Style.STROKE);
        //画圆
        canvas.drawCircle(pointX, pointY, radius, paint);
        canvas.save();
        canvas.rotate(degrees, pointX, pointY);

        //提供了一些api可以用来画线(画路径)
        Path path = new Path();
        //表示从那开始话，假如是A点
        path.moveTo(pointX + radius, pointY);
        ////从A点画一个直线到D点
        path.lineTo(pointX + radius - 20, pointY - 20);
        //从D点画一个直线到B点
        path.lineTo(pointX + radius, pointY + 20);
        //从B点画一个直线到C点
        path.lineTo(pointX + radius + 20, pointY - 20);
        //闭合  --  从C点画一个直线到A点
        path.close();
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint);
        canvas.restore();

        //旋转的度数一个一个度数增加,  如果乘以一个速度的话,按一个速度速度增加
        degrees += 1*speedDegrees;
        if (!isPause) {
            invalidate();
        }
    }

    public void setColor(int color) {
        if (colors != color) {
            colors = color;
        } else {
            colors = primevalColor;
        }
    }

    public void speed() {
        if (speedDegrees >= 10) {
            speedDegrees = 10;
            Toast.makeText(getContext(), "我比闪电还快", Toast.LENGTH_SHORT).show();
        }
        speedDegrees++;
    }

    public void slowDown() {
        speedDegrees--;
        if (speedDegrees <= 1) {
            speedDegrees = 1;
        }

    }

    public void pause() {
        //如果是开始状态的话去重新绘制
        if (isPause) {
            isPause = !isPause;
            invalidate();
        } else {
            isPause = !isPause;
        }
    }
}

