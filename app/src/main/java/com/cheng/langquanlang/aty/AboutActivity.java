package com.lql.cheng.aty;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.lql.cheng.R;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about_activity);
	}
	
}
