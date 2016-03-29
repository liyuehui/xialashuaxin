package com.example.pulltorefresh.ui;

import com.example.pulltorefresh.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MyLinearlayout extends LinearLayout {
	protected static final String TAG = MyLinearlayout.class.getSimpleName();



	public MyLinearlayout(Context context) {
		super(context);
		init();
	}

	public MyLinearlayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = View.inflate(getContext(), R.layout.mylinearlayout, this);
//		mListView.setOnTouchListener(this);
		Button button = (Button) view.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e(TAG,TAG+" button click");
			}
		});
		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.e(TAG,TAG+" myLayoutTouch");
				return false;
			}
		});;
	}

	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e(TAG,TAG+" dispatchTouchEvent:");
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return super.onTouchEvent(event);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.e(TAG,TAG+" onInterceptTouchEvent:");
		return true;
	}



}
