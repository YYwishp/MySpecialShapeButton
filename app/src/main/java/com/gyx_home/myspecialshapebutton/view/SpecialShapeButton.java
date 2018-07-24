package com.gyx_home.myspecialshapebutton.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.gyx_home.myspecialshapebutton.R;

/**
 * Created by gyx_home on 2018/7/24.
 */
public class SpecialShapeButton extends View {
	private Paint mPaint;
	private int mViewWidth, mViewHeight;// 控件宽高
	private Path mPath;
	float  toolbar=(float)getResources().getDimension(R.dimen.abc_action_bar_default_height_material);

	float statusbar=(float)getStatusBarHeight(super.getContext());
	int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	public SpecialShapeButton(Context context) {
		super(context);
	}

	public SpecialShapeButton(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		// 初始化画笔
		initPaint();
	}

	public SpecialShapeButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private void initPaint() {
		// 实例化画笔并打开抗锯齿
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.FILL);
		// 设置画笔颜色为浅灰色
		mPaint.setColor(Color.LTGRAY);
		mPaint.setStrokeWidth(10);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		/*
		 * 获取控件宽高
		 */
		mViewWidth = w;
		mViewHeight = h;
		Log.e("宽", mViewWidth + "");
		Log.e("高", mViewHeight + "");
		mPath = new Path();
		mPath.moveTo(mViewWidth / 2F + 100, mViewHeight / 2F - 50);
		mPath.lineTo(mViewWidth / 2F + 300, mViewHeight / 2F - 50);
		mPath.lineTo(mViewWidth / 2F + 300, mViewHeight / 2F + 200);
		mPath.lineTo(mViewWidth / 2F - 100, mViewHeight / 2F + 200);
		mPath.close();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);




		int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
		int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
		int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
		int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		/*
		 * 绘制一个红色矩形
		 */
		mPaint.setColor(Color.RED);
		Path path = new Path();
		path.moveTo(mViewWidth / 2F - 290,mViewHeight / 2F - 50);
		path.lineTo(mViewWidth / 2F + 290, mViewHeight / 2F - 50);
		path.quadTo(mViewWidth / 2F + 300, mViewHeight / 2F - 50, mViewWidth / 2F + 300, mViewHeight / 2F - 40);
		path.lineTo(mViewWidth / 2F + 300, mViewHeight / 2F + 190);
		path.quadTo(mViewWidth / 2F + 300, mViewHeight / 2F +200, mViewWidth / 2F + 290, mViewHeight / 2F + 200);
		path.lineTo(mViewWidth / 2F - 290, mViewHeight / 2F + 200);
		path.quadTo(mViewWidth / 2F - 300, mViewHeight / 2F +200, mViewWidth / 2F - 300, mViewHeight / 2F + 190);
		path.lineTo(mViewWidth / 2F - 300, mViewHeight / 2F - 40);
		path.quadTo(mViewWidth / 2F - 300, mViewHeight / 2F - 50, mViewWidth / 2F - 290, mViewHeight / 2F - 50);

		canvas.drawPath(path,mPaint);

//		canvas.drawRect(mViewWidth / 2F - 300, mViewHeight / 2F - 50, mViewWidth / 2F + 300, mViewHeight / 2F + 200, mPaint);

//		canvas.drawColor(Color.BLUE);
//		canvas.clipPath(mPath);
//		canvas.drawColor(Color.GREEN);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float y = event.getRawY()-toolbar-statusbar;
		float x = event.getRawX();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:

				break;

			case MotionEvent.ACTION_UP:

				//点击右边
				if (x > mViewWidth / 2F && x < mViewWidth / 2F + 300 && y > mViewHeight / 2F - 50 && y < mViewHeight / 2F + 200) {
					Path rightPath = new Path();
					rightPath.moveTo(mViewWidth / 2F + 100, mViewHeight / 2F - 50);
					rightPath.lineTo(mViewWidth / 2F + 300, mViewHeight / 2F - 50);
					rightPath.lineTo(mViewWidth / 2F + 300, mViewHeight / 2F + 200);
					rightPath.lineTo(mViewWidth / 2F - 100, mViewHeight / 2F + 200);
					rightPath.close();
					mPath = rightPath;


				}
				//点击左边
				if (x > mViewWidth / 2F - 300 && x < mViewWidth / 2F && y > mViewHeight / 2F - 50 && y < mViewHeight / 2F + 200) {
					Path leftPath = new Path();
					leftPath.moveTo(mViewWidth / 2F - 300, mViewHeight / 2F - 50);
					leftPath.lineTo(mViewWidth / 2F - 100, mViewHeight / 2F - 50);
					leftPath.lineTo(mViewWidth / 2F + 100, mViewHeight / 2F + 200);
					leftPath.lineTo(mViewWidth / 2F - 300, mViewHeight / 2F + 200);
					leftPath.close();
					mPath = leftPath;


				}

				postInvalidate();
				break;
		}


//		return super.onTouchEvent(event);
		return true;
	}
}



































