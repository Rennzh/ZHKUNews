package com.rainnka.zhkunews;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rainnka on 2016/5/13 13:28
 * Project name is ZHKUNews
 */
public class HomeActivityRecyclerViewIndicator extends LinearLayout {

	public List<View> viewList;
	public int VirtualSize;
	public TextView textView;

	public HomeActivityRecyclerViewIndicator(Context context) {
		super(context);
	}

	public HomeActivityRecyclerViewIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HomeActivityRecyclerViewIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setRecyclerViewIndicatorAttribute(int bannerSize, AppCompatActivity homeActivity) {
		VirtualSize = bannerSize + 2;
		viewList = new ArrayList<>();
		LinearLayout.LayoutParams layoutParamsNormal = new LinearLayout.LayoutParams
				(LengthTransitionUtility.dip2px(homeActivity, 16), ViewGroup
						.LayoutParams.MATCH_PARENT);
		layoutParamsNormal.setMargins(0, 0, LengthTransitionUtility.dip2px(homeActivity, 8), 0);
//		LinearLayout.LayoutParams layoutParamsEnd = new LinearLayout.LayoutParams(0, ViewGroup
//				.LayoutParams.MATCH_PARENT, 1);
		LinearLayout.LayoutParams layoutParamsEnd = new LinearLayout.LayoutParams
				(LengthTransitionUtility.dip2px(homeActivity, 16), ViewGroup
						.LayoutParams.MATCH_PARENT);
		layoutParamsEnd.setMargins(0, 0, 0, 0);
		for (int i = 0; i < bannerSize; i++) {
			textView = new TextView(homeActivity);
//			Log.i("ZRH", "" + 5);
			textView.setGravity(Gravity.CENTER);
//			Log.i("ZRH", "" + 6);
			//			if (i == 0) {
			//				Log.i("ZRH",""+5);
			//				viewList.get(0).setBackgroundResource(R.color.md_white_1000);
			//			} else {
			//				viewList.get(i).setBackgroundResource(R.color.md_grey_500);
			//			}
			if (i != bannerSize - 1) {
				textView.setLayoutParams(layoutParamsNormal);
			} else if (bannerSize == 1) {
				textView.setLayoutParams(layoutParamsEnd);
			} else {
				textView.setLayoutParams(layoutParamsEnd);
			}
			addView(textView);
			viewList.add(textView);
		}
	}

	public void setColorForStart() {
		if (viewList.get(0) != null) {
			Log.i("ZRH","not null");
			viewList.get(0).setBackgroundColor(Color.WHITE);
			viewList.get(1).setBackgroundColor(Color.GRAY);
			viewList.get(2).setBackgroundColor(Color.GRAY);
		}
	}

	public void changeColorForStatus(int position) {
		if (position == 0) {
			viewList.get(viewList.size() - 1).setBackgroundResource(R.color.homeActivityBannerIndicatorSelected);
			viewList.get(0).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
		} else if (position == VirtualSize - 1) {
			viewList.get(0).setBackgroundResource(R.color.homeActivityBannerIndicatorSelected);
			viewList.get(viewList.size() - 1).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
		} else {
			if (position == 1) {
				viewList.get(0).setBackgroundResource(R.color.homeActivityBannerIndicatorSelected);
				viewList.get(1).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
				viewList.get(viewList.size() - 1).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
			} else if (position == VirtualSize - 2) {
				viewList.get(viewList.size() - 1).setBackgroundResource(R.color.homeActivityBannerIndicatorSelected);
				viewList.get(viewList.size() - 2).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
				viewList.get(0).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
			} else {
				viewList.get(position - 1).setBackgroundResource(R.color.homeActivityBannerIndicatorSelected);
				viewList.get(position - 2).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
				viewList.get(position).setBackgroundResource(R.color.homeActivityBannerIndicatorUnSelected);
			}
		}
	}
}
