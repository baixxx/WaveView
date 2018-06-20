package com.bx.waveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.LinearInterpolator;

/**
*@author small white
*@date 2018/6/16
*@fuction android波浪效果,波浪线
*/
public class WaveView extends View{

    /**
     * 每个波浪宽度
     */
    private int mWaveWidth;
    /**
     * 波浪高度
     */
    private int mWaveHeight;

    /**
     * 波浪运动时间,控制波浪速度效果
     */
    private  int mWaveTime;
    /**
     * 波浪颜色
     */
    private  int mWaveColor;
    /**
     * 是否是波浪线
     */
    private  boolean isWaveLine;

    private int mWaveCount;
    private int offset;
    private int mScreenWidth;
    private int mScreenHeight;
    private Path mPath;
    private Paint mPaint;
    private ValueAnimator mValueAnimatior;
    private Canvas canvas;

    public WaveView(Context context) {
        this(context,null,0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.WaveViewStyle);
        mWaveTime = typedArray.getInteger(R.styleable.WaveViewStyle_mWaveTime,mWaveTime);
        mWaveColor = typedArray.getColor(R.styleable.WaveViewStyle_mWaveColor,mWaveColor);
        isWaveLine = typedArray.getBoolean(R.styleable.WaveViewStyle_isWaveLine,isWaveLine);
        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       setPaint();
    }

    /**
     * 设置paint
     */
    private void setPaint(){
        if (isWaveLine){
            mPaint.setStyle(Paint.Style.STROKE);
        }else {
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        mPaint.setStrokeWidth(8);
        mPaint.setColor(mWaveColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath = new Path();
        mScreenHeight = h;
        mScreenWidth = w;
        mWaveHeight = h/2;
        mWaveWidth = 800;
        // 计算波形的个数
        mWaveCount = (int) Math.round(mScreenWidth / mWaveWidth+1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //移动到屏幕外
        mPath.moveTo(-mWaveWidth*3/4,mWaveHeight);
        for (int i = 0; i < mWaveCount; i++) {
            //二阶贝塞尔曲线,绘制波形
            mPath.quadTo(-mWaveWidth * 3 / 4 + i * mWaveWidth + offset,mWaveHeight + 70,-mWaveWidth / 2 + i * mWaveWidth + offset,mWaveHeight);
            mPath.quadTo(-mWaveWidth / 4 + i * mWaveWidth + offset,mWaveHeight - 70,i * mWaveWidth + offset,mWaveHeight);
        }

        if (!isWaveLine){
            //铺满波浪线下方,形成封闭区
        mPath.lineTo(mScreenWidth,mScreenHeight);
        mPath.lineTo(0,mScreenHeight);
        mPath.close();
        }
        canvas.drawPath(mPath,mPaint);
        this.canvas = canvas;
    }


    /**
     * 开启波浪效果,设置偏移量
     */
    public void startAnimator() {
        mValueAnimatior = ValueAnimator.ofInt(0, mWaveWidth);
        mValueAnimatior.setDuration(mWaveTime);
        mValueAnimatior.setInterpolator(new LinearInterpolator());
        mValueAnimatior.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimatior.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimatior.start();
    }

    /**
     * 关闭波浪效果
     */
    public void closeAnimator() {
        if (mValueAnimatior.isRunning()){
            mValueAnimatior.cancel();
        }
    }


}
