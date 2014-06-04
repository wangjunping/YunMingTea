package com.imcore.yunmingtea.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.imcore.yunmingtea.R;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment implements OnClickListener{
	private ImageView mImgLogo;
	private EditText mEtTop;
	private ViewPager mViewPager;
	public static String IMG = "key";
	private ImageButton mImgBtn1,mImgBtn2,mImgBtn3,mImgBtn4;
	
	private int items;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		mImgLogo = (ImageView) view.findViewById(R.id.img_logo);
		mEtTop = (EditText) view.findViewById(R.id.et_top);
		
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager_tea);
		ViewPagerAdapter mAdapter = new ViewPagerAdapter();
		mViewPager.setAdapter(mAdapter);
		
		mImgBtn1 = (ImageButton) view.findViewById(R.id.imgbtn1);
		mImgBtn2 = (ImageButton) view.findViewById(R.id.imgbtn2);
		mImgBtn3 = (ImageButton) view.findViewById(R.id.imgbtn3);
		mImgBtn4 = (ImageButton) view.findViewById(R.id.imgbtn4);
//		mViewPager.setVisibility()
		mImgBtn1.setOnClickListener(this);
		mImgBtn2.setOnClickListener(this);
		mImgBtn3.setOnClickListener(this);
		mImgBtn4.setOnClickListener(this);
		
		autoMoveTopImages();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtn1:
			Intent intent1 = new Intent(getActivity(),
					ProductActivity.class);
			startActivity(intent1);
			break;
		case R.id.imgbtn2:
			Intent intent2 = new Intent(getActivity(),
					HotActivity.class);
			startActivity(intent2);
			break;
		case R.id.imgbtn3:
			Intent intent3 = new Intent(getActivity(),
					InformationActivity.class);
			startActivity(intent3);
			break;
		case R.id.imgbtn4:
			Intent intent4 = new Intent(getActivity(),
					ContactActivity.class);
			startActivity(intent4);
			break;
		}
		
	}
	
		private void autoMoveTopImages() {
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					ChangeTopImages aChangeTopImages = new ChangeTopImages();
					aChangeTopImages.execute(items);
					items++;
					if(items>5) {
						items = 0;
					}
				}
			};
			Timer timer = new Timer();
			timer.schedule(task,0,3000);
		}
		
		class ChangeTopImages extends AsyncTask<Integer, Void, Integer> {
			
			@Override
			protected void onPostExecute(Integer result) {
				mViewPager.setCurrentItem(result);
				super.onPostExecute(result);
			}

			@Override
			protected Integer doInBackground(Integer... params) {
				return params[0];
			}
	}
	}
