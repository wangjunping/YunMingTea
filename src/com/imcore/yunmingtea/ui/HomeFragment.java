package com.imcore.yunmingtea.ui;

import com.imcore.yunmingtea.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	private ImageView mImgLogo;
	private EditText mEtTop;
	private ViewPager mViewPager;
	public static String IMG = "key";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		mImgLogo = (ImageView) view.findViewById(R.id.img_logo);
		mEtTop = (EditText) view.findViewById(R.id.et_top);
		
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager_tea);
		ViewPagerAdapter mAdapter = new ViewPagerAdapter();
		mViewPager.setAdapter(mAdapter);
		return view;
	
	}
	
	class ViewPagerAdapter extends FragmentStatePagerAdapter{

		public ViewPagerAdapter() {
			super(getActivity().getSupportFragmentManager());
		}

		@Override
		public Fragment getItem(int position) {
			HomeImageFragment fmIamge = new HomeImageFragment();
			Bundle arg = new Bundle();
			arg.putInt(IMG, position);
			fmIamge.setArguments(arg);
			return fmIamge;
		}

		@Override
		public int getCount() {
			return 5;
		}
	}
	
	
}
