package com.imcore.yunmingtea.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.imcore.yunmingtea.R;
import com.imcore.yunmingtea.application.MyApplication;
import com.imcore.yunmingtea.image.ImageFetcher;

public class HomeImageFragment extends Fragment implements OnClickListener{
	private ImageView mImag;
	private ImageButton mImgBtn1,mImgBtn2,mImgBtn3,mImgBtn4;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.topimage_fragment, null);
		mImag = (ImageView) view.findViewById(R.id.img_fragment);
		Bundle arg = getArguments();
		int position = arg.getInt(HomeFragment.IMG);
		ImageFetcher fetcher = new ImageFetcher();
		fetcher.fetch(MyApplication.imgicUrl.get(position),mImag);
		
		mImgBtn1 = (ImageButton) view.findViewById(R.id.imgbtn1);
		mImgBtn2 = (ImageButton) view.findViewById(R.id.imgbtn2);
		mImgBtn3 = (ImageButton) view.findViewById(R.id.imgbtn3);
		mImgBtn4 = (ImageButton) view.findViewById(R.id.imgbtn4);
		return view;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.imgbtn1:
				
				break;
			case R.id.imgbtn2:
				
				break;
			case R.id.imgbtn3:
		
				break;
			case R.id.imgbtn4:
		
				break;
		}
	}
}
