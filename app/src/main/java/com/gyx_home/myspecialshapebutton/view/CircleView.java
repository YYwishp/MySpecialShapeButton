package com.gyx_home.myspecialshapebutton.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.gyx_home.myspecialshapebutton.R;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by gyx_home on 2018/7/30.
 */
public class CircleView extends View {
	private Paint mFirstPaint;
	private Paint bgPaint;
	private Paint secondPaint;
	private RectF rect1;
	private ArrayList<BigDecimal> arrayRadian;
	private Paint thirdPaint;
	private float circle_with;

	public CircleView(Context context) {
		this(context, null);
	}

	public CircleView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
		circle_with = typedArray.getDimension(R.styleable.CircleView_circle_with, 30);

		typedArray.recycle();

		// 初始化画笔
		initPaint();
	}
	private void initPaint() {
		// 实例化画笔并打开抗锯齿
		mFirstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mFirstPaint.setStyle(Paint.Style.STROKE);
		mFirstPaint.setColor(0xFF62ADEF);
		mFirstPaint.setStrokeWidth(circle_with);

		//
		bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bgPaint.setStyle(Paint.Style.STROKE);
		bgPaint.setColor(Color.WHITE);
		bgPaint.setStrokeWidth(circle_with);
		//
		secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		secondPaint.setColor(0xFFB7D5EE);
		secondPaint.setStyle(Paint.Style.STROKE);
		secondPaint.setStrokeWidth(circle_with);
		//
		thirdPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		thirdPaint.setColor(0xFFD8D9DC);
		thirdPaint.setStyle(Paint.Style.STROKE);
		thirdPaint.setStrokeWidth(circle_with);
		//

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


	private int mViewWidth, mViewHeight;// 控件宽高
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		mViewWidth = w;
		mViewHeight = h;
		rect1 = new RectF(circle_with/2, circle_with/2, mViewWidth- circle_with/2, mViewWidth- circle_with/2);

	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//背景白色
		canvas.drawColor(Color.WHITE);
		//背景环灰色
		canvas.drawCircle(mViewWidth/2, mViewHeight/2, mViewHeight/2- circle_with/2, bgPaint);
		if (arrayRadian != null && arrayRadian.size() > 0) {
			float per_0 = arrayRadian.get(0).floatValue();
			float per_1 = arrayRadian.get(1).floatValue();
			float per_2 = arrayRadian.get(2).floatValue();
//			Log.e("per_0", per_0 + "");
//			Log.e("per_1", per_1 + "");
//			Log.e("per_2", per_2 + "");



			float start = -180;
			canvas.drawArc(rect1, start, per_0, false, mFirstPaint);
			canvas.drawArc(rect1,start+per_0,per_1,false,secondPaint);
			canvas.drawArc(rect1,start+per_0+per_1,per_2+1,false,thirdPaint);

			//分割线
			canvas.drawArc(rect1,start-1,2,false,bgPaint);
			canvas.drawArc(rect1,start+per_0-1,2,false,bgPaint);
			canvas.drawArc(rect1,start+per_0+per_1-1,2,false,bgPaint);




		}

	}

	public void setMultiplePercent(String data[]) {
		BigDecimal total = new BigDecimal(0);
		ArrayList<BigDecimal> decimalArrayList = new ArrayList<>();
		for (String datum : data) {
			BigDecimal augend = new BigDecimal(datum);
			decimalArrayList.add(augend);
			total = total.add(augend);
		}

		//弧度
		arrayRadian = new ArrayList<>();
		for (BigDecimal bigDecimal : decimalArrayList) {
			BigDecimal radian = bigDecimal.divide(total, 100, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(360)).setScale(100, BigDecimal.ROUND_DOWN);
			arrayRadian.add(radian);
		}



		invalidate();



	}





}
























