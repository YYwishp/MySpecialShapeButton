package com.gyx_home.myspecialshapebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gyx_home.myspecialshapebutton.view.CircleView;
import com.gyx_home.myspecialshapebutton.view.SpecialShapeButton;

public class MainActivity extends AppCompatActivity {
	private SpecialShapeButton mMyButton;

	private CircleView circleview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);






		mMyButton = (SpecialShapeButton) findViewById(R.id.my_button);
		mMyButton.setStartText("测试测试");
		mMyButton.setEndText("11");
		mMyButton.setOnClickChangeListener(new SpecialShapeButton.OnClickChangeListener() {
			@Override
			public void onClickChangeListener(int i) {
				Toast.makeText(MainActivity.this, i + "", Toast.LENGTH_SHORT).show();
			}
		});












		circleview = (CircleView) findViewById(R.id.circleview);
		String data[] = {"1.2345", "0.8923", "2.9099"};
		circleview.setMultiplePercent(data);

	}
}
