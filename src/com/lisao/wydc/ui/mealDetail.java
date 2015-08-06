package com.lisao.wydc.ui;

import com.lisao.wydc.R;
import com.lisao.wydc.bean.Meal;
import com.lisao.wydc.config.Config;
import com.lisao.wydc.view.SmartImageView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class mealDetail extends ActivityBase implements OnClickListener {
	// 定义控件
	private SmartImageView imageView = null;
	private TextView content = null;
	private TextView price = null;
	private TextView oldPrice = null;
	private TextView dishids;
	private Button pay = null;

	private Meal meal = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置不显示标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mealdetail);
		meal = (Meal) getIntent().getSerializableExtra("meal");// 获得传数过来的序列化对象
		initLeftBtnAndTitle(meal.getName());
		init();
	}

	// 获得UI组件，并设置显示值
	private void init() {
		content = (TextView) findViewById(R.id.meal_detail_content);
		content.setText(meal.getContext());
		price = (TextView) findViewById(R.id.meal_newprice);
		price.setText("¥:" + meal.getNewprice() + "元");
		oldPrice = (TextView) findViewById(R.id.meal_oldprice);
		oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		oldPrice.setText(meal.getOldprice() + "元");
		dishids = (TextView) findViewById(R.id.meal_detail_dishids);
		dishids.setText(meal.getDishids());
		imageView = (SmartImageView) findViewById(R.id.meal_pic);
		LayoutParams paramas = (LayoutParams) imageView.getLayoutParams();
		paramas.height = mScreenWidth / 2;// 修改控件的高等于宽的值
		paramas.width = mScreenWidth;
		imageView.setLayoutParams(paramas);
		imageView.setImageUrl(Config.IP_ADDRESS + "/" + meal.getImageurl());
		pay = (Button) findViewById(R.id.meal_detail_select);
		pay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.meal_detail_select:
			Intent mIntent = new Intent(getApplicationContext(), Change.class);
			mIntent.putExtra("meal", meal);
			startActivity(mIntent);
			break;
		}
	}
}
