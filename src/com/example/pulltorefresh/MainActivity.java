package com.example.pulltorefresh;

import java.util.ArrayList;

import com.example.pulltorefresh.ui.PullToRefresh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private PullToRefresh mPullToRefresh;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mPullToRefresh = (PullToRefresh) findViewById(R.id.pullToRefresh);
		
		mListView = mPullToRefresh.getListView();
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 20; i++){
			list.add("item"+i);
		}
		
		mListView.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.item_textview,list));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Toast.makeText(MainActivity.this,"hehe", Toast.LENGTH_SHORT).show();
					
			}
		});
	}


}
