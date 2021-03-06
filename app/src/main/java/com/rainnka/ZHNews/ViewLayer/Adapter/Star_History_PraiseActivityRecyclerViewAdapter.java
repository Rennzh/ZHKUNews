package com.rainnka.ZHNews.ViewLayer.Adapter;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rainnka.ZHNews.ViewLayer.Activity.Star_History_PraiseAty;
import com.rainnka.ZHNews.Application.BaseApplication;
import com.rainnka.ZHNews.Bean.ZhiHuNewsItemInfo;
import com.rainnka.ZHNews.R;
import com.rainnka.ZHNews.Utility.ConstantUtility;
import com.rainnka.ZHNews.Utility.SnackbarUtility;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by rainnka on 2016/5/21 14:54
 * Project name is ZHKUNews
 */
public class Star_History_PraiseActivityRecyclerViewAdapter extends RecyclerView
		.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

	public WeakReference<AppCompatActivity> appCompatActivityWeakReference;
	public AppCompatActivity appCompatActivity;
	public LayoutInflater layoutInflater;
	public List<ZhiHuNewsItemInfo> zhiHuNewsItemInfoList;

	public boolean mIsSelected = false;
	public SparseBooleanArray mSelectedPositions = new SparseBooleanArray();

	public SQLiteDatabase sqLiteDatabase;

	public Star_History_PraiseActivityRecyclerViewAdapter(AppCompatActivity appCompatActivity) {
		this.appCompatActivityWeakReference = new WeakReference<>
				(appCompatActivity);
		this.appCompatActivity = this.appCompatActivityWeakReference.get();
		layoutInflater = LayoutInflater.from(this.appCompatActivity);
	}

	public void setZhiHuNewsItemInfoList(List<ZhiHuNewsItemInfo> zhiHuNewsItemInfoList) {
		this.zhiHuNewsItemInfoList = zhiHuNewsItemInfoList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new RecyclerViewContentViewHolder(layoutInflater.inflate(R.layout
				.star_history_praise_aty_content_recyclerview_item, parent, false));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof RecyclerViewContentViewHolder) {
			ZhiHuNewsItemInfo zhiHuNewsItemInfo = zhiHuNewsItemInfoList.get(position);
			RecyclerViewContentViewHolder recyclerViewContentViewHolder =
					(RecyclerViewContentViewHolder) holder;
			recyclerViewContentViewHolder.title_tv.setText(zhiHuNewsItemInfo.title);
			if (getItemChecked(position)) {
				recyclerViewContentViewHolder.check_tv.setVisibility(View.VISIBLE);
			} else {
				recyclerViewContentViewHolder.check_tv.setVisibility(View.GONE);
			}
			if(zhiHuNewsItemInfo.images.get(0).equals("")){
				Glide.with(appCompatActivity)
						.load(R.drawable.drawer_header_bg)
						.crossFade(0)
						.into(recyclerViewContentViewHolder.image_iv);
			}else {
				Glide.with(appCompatActivity)
						.load(zhiHuNewsItemInfo.images.get(0))
						.crossFade(0)
						.skipMemoryCache(true)
						.into(recyclerViewContentViewHolder.image_iv);
			}
		}
	}

	@Override
	public int getItemCount() {
		return zhiHuNewsItemInfoList.size();
	}

	@Override
	public void onItemDismiss(int targetPosition) {
		try {
			int itemId = zhiHuNewsItemInfoList.get(targetPosition).id;
			if (sqLiteDatabase == null) {
				sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(BaseApplication
						.getDATABASE_PATH() + "/myInfo.db3", null);
			}
			int deleteCount = 0;
			if (((Star_History_PraiseAty) appCompatActivity).title.equals(ConstantUtility.STAR_KEY)) {
				deleteCount = sqLiteDatabase.delete("my_star", "ItemId like ?", new
						String[]{String.valueOf
						(itemId)});
			} else {
				deleteCount = sqLiteDatabase.delete("my_praise", "ItemId like ?", new String[]{String.valueOf
						(itemId)});
			}
			if (deleteCount > 0) {
				SnackbarUtility.getSnackbarDefault(((Star_History_PraiseAty) appCompatActivity)
						.coordinatorLayout, "删除成功", Snackbar.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Log.i("ZRH", "exception in onItemDismiss" + e.toString());
			Log.i("ZRH", "exception in onItemDismiss" + e.getMessage());
		}
		zhiHuNewsItemInfoList.remove(targetPosition);
		notifyItemRemoved(targetPosition);
	}

	public void setSelectable(boolean selectable) {
		mIsSelected = selectable;
	}

	public void setMultiSelectable(RecyclerView.ViewHolder viewHolder) {
		int lastPostionInSparse = mSelectedPositions.keyAt(mSelectedPositions.size() - 1);
		int position = viewHolder.getAdapterPosition();
		if (position > lastPostionInSparse) {
			for (int i = lastPostionInSparse + 1; i <= position; i++) {
				setItemChecked(i, true);
			}
		} else {
			for (int i = position; i < lastPostionInSparse; i++) {
				setItemChecked(i, true);
			}
		}

	}

	public boolean getSelectable() {
		return mIsSelected;
	}

	public void setItemChecked(int position, boolean checked) {
		mSelectedPositions.put(position, checked);
	}

	public boolean getItemChecked(int position) {
		return mSelectedPositions.get(position, false);
	}

	public void setSignVisibility(RecyclerView.ViewHolder viewHolder, int visibility) {
		((Star_History_PraiseActivityRecyclerViewAdapter
				.RecyclerViewContentViewHolder) viewHolder).check_tv.setVisibility
				(visibility);
//		if (visibility == View.GONE || visibility == View.INVISIBLE) {
//			removeSelectedItem(viewHolder.getAdapterPosition());
//		} else {
//			setItemChecked(viewHolder.getAdapterPosition(), true);
//		}
	}

	public void removeSelectedItem(int position) {
		mSelectedPositions.delete(position);
	}

	public void setAllItemChecked() {
		for (int i = 0; i < zhiHuNewsItemInfoList.size(); i++) {
			setItemChecked(i, true);
		}
	}

	public void clearCheckedItem() {
		mSelectedPositions.clear();
	}

	public static class RecyclerViewContentViewHolder extends RecyclerView.ViewHolder {

		public ImageView image_iv;
		public TextView title_tv;
		public TextView check_tv;

		public RecyclerViewContentViewHolder(View itemView) {
			super(itemView);

			image_iv = (ImageView) itemView.findViewById(R.id
					.star_history_praise_activity_content_recyclerview_item_picture_iv);
			title_tv = (TextView) itemView.findViewById(R.id
					.star_history_praise_activity_content_recyclerview_item_content_tv);
			check_tv = (TextView) itemView.findViewById(R.id
					.star_history_praise_activity_content_recyclerview_item_checkmark);
		}

	}
}
