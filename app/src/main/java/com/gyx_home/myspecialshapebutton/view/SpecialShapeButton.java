package com.gyx_home.myspecialshapebutton.view;

import android.content.Context;
import android.content.res.TypedArray;
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

	private Paint mPaint;
	private int mViewWidth, mViewHeight;// 控件宽高
	private Path mPath;
	private Path startPath;
	private float textSize;
	private int text_color;
	private float radian;
	private int bg_color;
	private int selected_color;
	private String startText;
	private String endText;
	private float off_x;
	private OnClickChangeListener mListener;
	private boolean isLeft = true;
	private Path rightPath;
	private Path leftPath;

	public void setStartText(String startText) {
		this.startText = startText;
		invalidate();
	}

	public void setEndText(String endText) {
		this.endText = endText;
		invalidate();
	}

	public SpecialShapeButton(Context context) {
		this(context, null);
	}

	public SpecialShapeButton(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public SpecialShapeButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);



		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SpecialShapeButton);
		textSize = typedArray.getDimension(R.styleable.SpecialShapeButton_text_size, 20);
		text_color = typedArray.getColor(R.styleable.SpecialShapeButton_text_color, Color.parseColor("#333333"));
		radian = typedArray.getDimension(R.styleable.SpecialShapeButton_circular, 80);
		off_x = typedArray.getDimension(R.styleable.SpecialShapeButton_offX, 10);
		bg_color = typedArray.getColor(R.styleable.SpecialShapeButton_bg_color, Color.parseColor("#DCDCDC"));
		selected_color = typedArray.getColor(R.styleable.SpecialShapeButton_selected_color, Color.parseColor("#ffffff"));




		Log.e("textSize", textSize + "");
		Log.e("text_color", text_color + "");
		Log.e("radian", radian + "");
		Log.e("bg_color", bg_color + "");
		Log.e("selected_color", selected_color + "");


		// 初始化画笔
		initPaint();
	}



	private void initPaint() {
		// 实例化画笔并打开抗锯齿
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(bg_color);
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
		mPath.lineTo(mViewWidth, mViewHeight);

		mPath.lineTo(0, mViewHeight);

		mPath.lineTo(0, radian);
		mPath.quadTo(0, 0, radian, 0);
		mPath.close();
		//开始是左边
		startPath = new Path();
		startPath.moveTo(radian, 0);
		startPath.lineTo(mViewWidth / 2 - off_x, 0);
		startPath.lineTo(mViewWidth / 2 + off_x, mViewHeight);
		startPath.lineTo(0, mViewHeight);

		startPath.lineTo(0, radian);
		startPath.quadTo(0, 0, radian, 0);
		startPath.close();
		//左边
		rightPath = new Path();
		rightPath.moveTo(mViewWidth / 2F + off_x, 0);
		rightPath.lineTo(mViewWidth -radian, 0);
		rightPath.quadTo(mViewWidth, 0, mViewWidth, radian);
		rightPath.lineTo(mViewWidth , mViewHeight);

		rightPath.lineTo(mViewWidth / 2F - off_x, mViewHeight);
		rightPath.lineTo(mViewWidth / 2F + off_x, 0);


		rightPath.close();



		//右边
		leftPath = new Path();
		leftPath.moveTo(radian, 0);
		leftPath.lineTo(mViewWidth / 2 - off_x, 0);
		leftPath.lineTo(mViewWidth / 2 + off_x, mViewHeight);
		leftPath.lineTo(0, mViewHeight);

		leftPath.lineTo(0, radian);
		leftPath.quadTo(0, 0, radian, 0);
		leftPath.close();
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



		//选中颜色
		mPaint.setColor(selected_color);
		//按照路线切割带有圆弧的路径
		canvas.clipPath(mPath);
		//画背景色
		canvas.drawColor(bg_color);
		//画出梯形路径
		canvas.drawPath(startPath, mPaint);

		//
		//计算出baseLine位置
		mPaint.setTextSize(textSize);
		Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
//		Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//		Log.e("ascent", fontMetrics.ascent + "");
//		Log.e("descent", fontMetrics.descent + "");
//		Log.e("top", fontMetrics.top + "");
//		Log.e("bottom", fontMetrics.bottom + "");

		//基线
		int baseLineY = mViewHeight/2 + (fm.bottom - fm.top)/2 - fm.bottom;
		//画基线
//		mPaint.setColor(Color.YELLOW);
//		canvas.drawLine(0, baseLineY, 3000, baseLineY, mPaint);
		mPaint.setColor(text_color);
		float startTextwith = mPaint.measureText(startText);
		float endTextwith = mPaint.measureText(endText);
		//写文字
		canvas.drawText(startText, (mViewWidth/2-startTextwith)/2, baseLineY, mPaint);
		canvas.drawText(endText, (mViewWidth/2-endTextwith)/2+(mViewWidth/2), baseLineY, mPaint);



	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		float y = event.getRawY() - toolbar - statusbar;
//		float x = event.getRawX();
		float y = event.getY();
		float x = event.getX();



		Log.e("x----", x + "");
		Log.e("Y----", y + "");
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP:
				//点击右边
				if (x > mViewWidth / 2F && x < mViewWidth  && y > 0 && y < mViewHeight) {
//					Path rightPath = new Path();
//					rightPath.moveTo(mViewWidth / 2F + off_x, 0);
//					rightPath.lineTo(mViewWidth -radian, 0);
//					rightPath.quadTo(mViewWidth, 0, mViewWidth, radian);
//					rightPath.lineTo(mViewWidth , mViewHeight);
//
//					rightPath.lineTo(mViewWidth / 2F - off_x, mViewHeight);
//					rightPath.lineTo(mViewWidth / 2F + off_x, 0);
//
//
//					rightPath.close();
					startPath = rightPath;
					if (mListener != null&& isLeft) {
						mListener.onClickChangeListener(1);
						isLeft = false;
					}



				}
				//点击左边
				if (x > 0 && x < mViewWidth / 2F && y > 0 && y < mViewHeight) {
//					Path leftPath = new Path();
//					leftPath.moveTo(radian, 0);
//					leftPath.lineTo(mViewWidth / 2 - off_x, 0);
//					leftPath.lineTo(mViewWidth / 2 + off_x, mViewHeight);
//					leftPath.lineTo(0, mViewHeight);
//
//					leftPath.lineTo(0, radian);
//					leftPath.quadTo(0, 0, radian, 0);
//					leftPath.close();

					startPath = leftPath;
					if (mListener != null&& !isLeft) {
						mListener.onClickChangeListener(0);
						isLeft = true;

					}
				}
				postInvalidate();
				break;
		}
//		return super.onTouchEvent(event);
		return true;
	}

	public void setOnClickChangeListener(OnClickChangeListener listener) {
		this.mListener = listener;
	}

	public interface OnClickChangeListener {
		void onClickChangeListener(int i);
	}

}



































