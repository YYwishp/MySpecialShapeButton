package com.gyx_home.myspecialshapebutton.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by gyx_home on 2018/7/30.
 */
public class CircleView extends View {
	private Paint mPaint;
	private Paint whitePaint;
	private Paint secondPaint;

	public CircleView(Context context) {
		super(context);
	}

	public CircleView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		// 初始化画笔
		initPaint();
	}

	public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

	}
	private void initPaint() {
		// 实例化画笔并打开抗锯齿
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.STROKE);
		// 设置画笔颜色为浅灰色
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(20);

		//
		whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		whitePaint.setStyle(Paint.Style.STROKE);
		whitePaint.setColor(Color.GRAY);
		whitePaint.setStrokeWidth(20);
		//
		secondPaint = new Paint();
		secondPaint.setColor(Color.RED);
		secondPaint.setStyle(Paint.Style.STROKE);
		secondPaint.setStrokeWidth(20);


	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(Color.WHITE);
		canvas.drawCircle(100, 100, 90, whitePaint);

		RectF rect1 = new RectF(10, 10, 190, 190);
		mPaint.setColor(Color.BLUE);
		canvas.drawArc(rect1, -180, 270, false, mPaint);
		canvas.drawArc(rect1,92,40,false,secondPaint);


	}



}
