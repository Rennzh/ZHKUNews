package com.rainnka.ZHNews.ViewLayer.Activity.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

/**
 * Created by rainnka on 2016/5/17 14:11
 * Project name is ZHKUNews
 */
public class BaseAty extends AppCompatActivity {

//	private View statusBarInstead;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//				if (Build.VERSION.SDK_INT <= 19 && Build.VERSION.SDK_INT >= 14) {
		//					ViewGroup firstChildView = (ViewGroup) ((ViewGroup) getWindow().getDecorView()).getChildAt(0);
		//			statusBarInstead = new View(this);
		//
		//			/*
		//			* 创建statusBarInstead用的布局参数
		//			* */
		//			ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams
		//					(ViewGroup
		//					.LayoutParams.MATCH_PARENT, getStatusBarHeight());
		//			/*
		//			* 默认颜色透明
		//			* 可以自定义
		//			* */
		//			statusBarInstead.setLayoutParams(layoutParams);
		//			statusBarInstead.setBackgroundColor(Color.TRANSPARENT);
		//
		//			/*
		//			* 在第一个位置填充上statusBarInstead
		//			* */
		//			firstChildView.addView(statusBarInstead, 0);
		//				}

		//		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
		//			Window window = getWindow();
		//			// Translucent status bar
		//			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager
		//					.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager
		//					.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		//		}

		/*
		* 控制底部导航栏半透明
		* */
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//			getWindow().getDecorView().setSystemUiVisibility(View
//					.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//		}
	}

	public void setupWindowAnimations() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			//			Fade fade = new Fade();
			//			fade.setDuration(500);
			//			Explode explode = new Explode();
			//			explode.setDuration(300);
			Slide slide = new Slide();
			slide.setSlideEdge(Gravity.RIGHT);
			slide.setDuration(150);
			getWindow().setEnterTransition(slide);
			//			getWindow().setReturnTransition(slide);
		}
	}

	public void setFullScreenLayout(){
		getWindow().getDecorView().setSystemUiVisibility(View
				.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
	}

	public void setFullScreen(){
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
						| View.SYSTEM_UI_FLAG_IMMERSIVE);
	}
}
