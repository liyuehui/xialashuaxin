package com.example.pulltorefresh.ui;

import com.example.pulltorefresh.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PullToRefresh extends LinearLayout {
	protected static final String TAG = PullToRefresh.class.getSimpleName();

	static final int PULL_REFRESH = 1;// 下拉刷新
	static final int REFRESHING = 2; // 正在刷新
	static final int RELEADE_REFRESH = 3; // 释放刷新

	private View mHeaderView;
	private TextView mHeaderTitle;
	private int mHeaderHeight;

	private ListView mListView;

	private int mCurState = PULL_REFRESH;

	private float mDownY;

	public PullToRefresh(Context context) {
		super(context);
		init();
	}

	public PullToRefresh(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PullToRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = View.inflate(getContext(), R.layout.refresh_view, this);
		mHeaderView = view.findViewById(R.id.ll_header);
		mHeaderTitle = (TextView) view.findViewById(R.id.tv_title);
		mListView = (ListView) view.findViewById(R.id.listView);
		getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				mHeaderHeight = mHeaderView.getMeasuredHeight();
				if (mHeaderHeight != 0) {
					getViewTreeObserver().removeGlobalOnLayoutListener(this);
					resetHeader();

					Log.e(TAG, TAG + " mHeaderHeight:" + mHeaderHeight + " top:"
							+ ((LinearLayout.LayoutParams) mHeaderView.getLayoutParams()).topMargin);
				}
			}
		});
	}

	public ListView getListView() {
		return mListView;
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownY = event.getRawY();
			mCurState = RELEADE_REFRESH;// 下拉刷新
			Log.e(TAG, TAG + " mDownY:" + mDownY);
			
			break;

		case MotionEvent.ACTION_MOVE:
			float deltaY = event.getRawY() - mDownY;
			Log.e(TAG, TAG + " deltaY:" + deltaY);
			if (deltaY > 0) {
				LinearLayout.LayoutParams p = (LayoutParams) mHeaderView.getLayoutParams();
				p.topMargin = (int) (-mHeaderHeight + deltaY);
				mHeaderView.setLayoutParams(p);
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mCurState = PULL_REFRESH;
			resetHeader();
			if (mCurState == RELEADE_REFRESH) {
				if (getTopMarginTop() >= 0) {
					mCurState = REFRESHING;
				} else {

					mCurState = PULL_REFRESH;
					resetHeader();
				}
			}
			mDownY = 0;
			Log.e(TAG, TAG + " ACTION_UP " +" is:"+ Boolean.valueOf(RELEADE_REFRESH == mCurState));
			break;
		}

		if(RELEADE_REFRESH == mCurState){
			return true;
		}
		// if (mCurState == REFRESHING) {
		// return true;
		// }
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {

		boolean intercept = false;
		
//		if(RELEADE_REFRESH == mCurState){
//			Log.e(TAG, TAG + " RELEADE_REFRESH == mCurState");
//		}
		
//		Log.e(TAG, TAG + " onInterceptTouchEvent:"+ getTopMarginTop());
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, TAG + "ACTION_MOVE");
			float dy = event.getRawY() - mDownY;
			if (Math.abs(dy) > 0)   {
				intercept = true;
				
			}else{
				intercept = false;
			}
			break;
		case MotionEvent.ACTION_DOWN:
			mDownY = event.getRawY();
			Log.e(TAG, TAG + "ACTION_DOWN");
			if (getTopMarginTop() == -mHeaderHeight )  {
				Log.e(TAG, TAG + "getTopMarginTop():"+getTopMarginTop());
				
			}
			break;

		}
		Log.e(TAG, TAG+" intercept:"+intercept);
		return intercept;
	}

	private int getTopMarginTop() {
		int topMar = ((LinearLayout.LayoutParams) mHeaderView.getLayoutParams()).topMargin;
		return topMar;
	}

	private void resetHeader() {
		LinearLayout.LayoutParams p = (LayoutParams) mHeaderView.getLayoutParams();
		p.topMargin = -mHeaderHeight;
		mHeaderView.setLayoutParams(p);
	}


}
