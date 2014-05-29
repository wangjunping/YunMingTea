package com.imcore.yunmingtea.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.imcore.yunmingtea.R;

public class MainActivity extends FragmentActivity {
	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTabHost = (FragmentTabHost) findViewById(R.id.tab_host);
		mTabHost.setup(this, getSupportFragmentManager(),
				R.id.layout_frgmt_container);

		TabSpec specHome = mTabHost.newTabSpec("home");
		View indicator1 = getIndicator("首页", R.drawable.tab_home_normal);
		specHome.setIndicator(indicator1);
		mTabHost.addTab(specHome, HomeFragment.class, null);

		TabSpec specMall = mTabHost.newTabSpec("mall");
		View indicator2 = getIndicator("商城", R.drawable.tab_mall_normal);
		specMall.setIndicator(indicator2);
		mTabHost.addTab(specMall, MallFragment.class, null);
		
		TabSpec specMine = mTabHost.newTabSpec("mine");
		View indicator3 = getIndicator("我的", R.drawable.tab_mine_normal);
		specMine.setIndicator(indicator3);
		mTabHost.addTab(specMine, MineFragment.class, null);
		
		TabSpec specMore = mTabHost.newTabSpec("more");
		View indicator4 = getIndicator("更多", R.drawable.tab_more_normal);
		specMore.setIndicator(indicator4);
		mTabHost.addTab(specMore, MoreFragment.class, null);
	}

	private View getIndicator(String text, int drawableId) {
		View view = getLayoutInflater().inflate(R.layout.tab_indicator, null);
		TextView tv = (TextView) view.findViewById(R.id.tv_indicator);
		tv.setText(text);
		Drawable top = getResources().getDrawable(drawableId);
		tv.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
		return view;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
