package com.gyx_home.myspecialshapebutton.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
	float toolbar = (float) getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
	float radian = 80f;
	float statusbar = (float) getStatusBarHeight(super.getContext());
	private Paint mPaint;
	private int mViewWidth, mViewHeight;// 控件宽高
	private Path mPath;
	private Path startPath;

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

	int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
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
		mPath.moveTo(radian, 0);
		mPath.lineTo(mViewWidth - radian, 0);
		mPath.quadTo(mViewWidth, 0, mViewWidth, radian);
		mPath.lineTo(mViewWidth, mViewHeight - radian);
		mPath.quadTo(mViewWidth, mViewHeight, mViewWidth - radian, mViewHeight);
		mPath.lineTo(radian, mViewHeight);
		mPath.quadTo(0, mViewHeight, 0, mViewHeight - radian);
		mPath.lineTo(0, radian);
		mPath.quadTo(0, 0, radian, 0);
		mPath.close();
		//左边
		startPath = new Path();
		startPath.moveTo(radian, 0);
		startPath.lineTo(mViewWidth / 2 - 100, 0);
		startPath.lineTo(mViewWidth / 2 + 100, mViewHeight);
		startPath.lineTo(radian, mViewHeight);
		startPath.quadTo(0, mViewHeight, 0, mViewHeight - radian);
		startPath.lineTo(0, radian);
		startPath.quadTo(0, 0, radian, 0);
		startPath.close();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
		int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
		int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
		int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
		setMeasuredDimension(measureWidth, measureHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		/*
		 * 绘制一个红色矩形
		 */
		mPaint.setColor(Color.RED);
		canvas.drawColor(Color.BLUE);
		canvas.clipPath(mPath);
		canvas.drawColor(Color.GREEN);
		canvas.drawPath(startPath, mPaint);

		//
		//计算出baseLine位置
		mPaint.setTextSize(100);
		Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
		Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

		Log.e("ascent", fontMetrics.ascent + "");
		Log.e("descent", fontMetrics.descent + "");
		Log.e("top", fontMetrics.top + "");
		Log.e("bottom", fontMetrics.bottom + "");


		int baseLineY = mViewHeight/2 + (fm.bottom - fm.top)/2 - fm.bottom;
		//画基线
		mPaint.setColor(Color.YELLOW);
		canvas.drawLine(0, baseLineY, 3000, baseLineY, mPaint);
		mPaint.setColor(Color.BLACK);

		//写文字
		canvas.drawText("测试", 0, baseLineY, mPaint);



	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float y = event.getRawY() - toolbar - statusbar;
		float x = event.getRawX();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP:
				//点击右边
				if (x > mViewWidth / 2F && x < mViewWidth  && y > 0 && y < mViewHeight) {
					Path rightPath = new Path();
					rightPath.moveTo(mViewWidth / 2F + 100, 0);
					rightPath.lineTo(mViewWidth -radian, 0);
					rightPath.quadTo(mViewWidth, 0, mViewWidth, radian);
					rightPath.lineTo(mViewWidth , mViewHeight - radian);

					rightPath.quadTo(mViewWidth, mViewHeight, mViewWidth-radian, mViewHeight);
					rightPath.lineTo(mViewWidth / 2F - 100, mViewHeight);
					rightPath.lineTo(mViewWidth / 2F + 100, 0);


					rightPath.close();
					startPath = rightPath;
				}
				//点击左边
				if (x > mViewWidth / 2F - 300 && x < mViewWidth / 2F && y > mViewHeight / 2F - 50 && y < mViewHeight / 2F + 200) {
					Path leftPath = new Path();
					leftPath.moveTo(radian, 0);
					leftPath.lineTo(mViewWidth / 2 - 100, 0);
					leftPath.lineTo(mViewWidth / 2 + 100, mViewHeight);
					leftPath.lineTo(radian, mViewHeight);
					leftPath.quadTo(0, mViewHeight, 0, mViewHeight - radian);
					leftPath.lineTo(0, radian);
					leftPath.quadTo(0, 0, radian, 0);
					leftPath.close();
					startPath = leftPath;
				}
				postInvalidate();
				break;
		}
//		return super.onTouchEvent(event);
		return true;
	}
}



































